package concurrent;

import java.util.concurrent.*;

/**
 * ClassName:ExecutorServceTest
 * Description:
 *
 * @Date:2022/5/11 15:54
 * @Author:cmt
 */
public class ExecutorServceTest {
    public static void main(String[] args) {
        // 三个无用的线程池
        // 创建单个线程
        ExecutorService service = Executors.newSingleThreadExecutor();
        // 5 个线程
        service = Executors.newFixedThreadPool(5);
        // 遇强则强，当有任务需要线程时，若此时没有线程就会创建一个来执行任务
        service = Executors.newCachedThreadPool();

        /**
         * 最大线程如何定义
         * 1.CPU 密集型  几何就是几个
         * 2.IO 密集型
         */
        // 多少核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 自定义线程池
        service = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 默认拒绝策略
        /**
         *  最大承载线程：Deque + maxSize;
         * ThreadPoolExecutor.AbortPolicy());
         * // 默认拒绝策略  一旦超过最大承载线程就抛出异常
         * ThreadPoolExecutor.CallerRunsPolicy()
         * // 哪来的去哪里
         * ThreadPoolExecutor.DiscardPolicy()
         * // 队列满了就丢弃不抛出异常
         * ThreadPoolExecutor.DiscardOldestPolicy()
         * // 队列满了，尝试和最早的竞争，成功就上位，失败就丢弃，不抛出异常
         */
        try {
            for (int i = 0; i < 20; i++) {
                service.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完关闭线程池
            service.shutdown();
        }
        service.shutdown();
        System.out.println("???????????????");
    }
}
