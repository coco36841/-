package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ClassName:CyclicBarrierTest
 * Description: 加法计数器
 *
 * @Date:2022/5/11 12:33
 * @Author:cmt
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        // 集齐七颗龙珠召唤神龙
        // 两个参数, 第一个需要创建线程个数，第二个 Runnable
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤神龙成功");
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            System.out.println(cyclicBarrier.getParties() + "<-----------" + cyclicBarrier.isBroken());
            System.out.println(cyclicBarrier.getNumberWaiting());
            // 下面的那个类只能拿到 final 类型
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集到了第 " + (temp+1) + " 颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                list.add(temp);
            }).start();
//            if (i==3) {
//                cyclicBarrier.reset();
//            }
        }
        Thread.sleep(5000);
        System.out.println("55555555555555555555555555555");
        System.out.println(cyclicBarrier.getParties() + "<-----------" + cyclicBarrier.isBroken());
        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(list);
    }
}
