package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:AtomicTest
 * Description: 保证原子性的包装类
 * 类底层直接和操作系统挂钩，直接在内存修改值！unsafe
 * CAS
 * @Date:2022/5/13 9:46
 * @Author:cmt
 */
public class AtomicTest {

    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        int i = num.addAndGet(1);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
//        int nub = Thread.activeCount();// 现在正在活动的所有线程，包括main，gcc
        while (Thread.activeCount() > 2) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Thread.yield();
        }
        System.out.println(num);
    }
}
