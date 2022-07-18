package ProducerConsumerProblem;

/**
 * ClassName:A
 * Description:
 *
 * @Date:2022/5/11 8:41
 * @Author:cmt
 */

public class synchronizedA {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"A").run();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"B").start();
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                data.increment();
//            }
//        },"C").start();
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                data.decrement();
//            }
//        },"D").start();
    }


}

class Data{

    private int num = 0;

    public synchronized void increment(){
        // while 防止虚假唤醒
        while (num > 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName() + " -> " + num);
        this.notifyAll();
    }
    public synchronized void decrement(){
        while (num <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName() + " -> " + num);
        this.notifyAll();
    }
}