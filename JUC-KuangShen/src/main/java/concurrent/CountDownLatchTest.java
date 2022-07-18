package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * ClassName:CountDownLatchTest
 * Description: 减法计数器
 *
 * @Date:2022/5/11 12:28
 * @Author:cmt
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 六个线程，执行完就完了
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"已运行");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 等待计数器归 0 (6个线程执行完毕),再向下执行
        countDownLatch.await();

        System.out.println("运行结束");
    }

}
