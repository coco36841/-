package ProducerConsumerProblem;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:eightSUO
 * Description: 8锁,就是关于锁的八个问题
 *
 * @Date:2022/5/11 9:21
 * @Author:cmt
 */
public class eightSUO {
    public static void main(String[] args) {
        Phone phone2 = new Phone();
        Phone phone1 = new Phone();

        new Thread(()->{
            try {
                phone1.sendS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
//        try {
//            // 停一秒
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(()->{
            try {
                phone1.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
class Phone{

    public static synchronized void sendS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("发短信");
    }

    public synchronized void call()throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("打电话");
    }

    public void hello(){
        System.out.println("hello");
    }
}