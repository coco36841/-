package concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:SemaphoreTest
 * Description: 信号量
 *
 * @Date:2022/5/11 12:43
 * @Author:cmt
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        // 线程数量，停车位,限流量！
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {

            new Thread(()->{
                try {
//                    System.out.println();
                    // 得到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放车位并唤醒别的等待的
                    semaphore.release();
                }
            },String.valueOf(i)).start();

        }
    }
}
