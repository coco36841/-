package Lock;

/**
 * ClassName:synchronTest
 * Description: 可重入锁
 *
 * @Date:2022/5/13 21:33
 * @Author:cmt
 */
public class synchronTest {
    public static void main(String[] args) {
        Phone phone = new Phone();

        /**
         * 执行结果一定是 sms后面跟着 call
         * 因为 包含关系的重入锁不需要重新获取锁。
         * */
        new Thread(()->{
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            phone.sms();
        },"A").start();

//        new Thread(()->{
//            phone.sms();
//        },"B").start();
    }

}

class Phone{

    int sta = 0;

    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName() + "sms");
//        call();
        sta++;
        if (sta < 10)
        sms();
//        for (int i = 0; i < 2; i++) {
//            call();
//        }
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + "call");
    }
}