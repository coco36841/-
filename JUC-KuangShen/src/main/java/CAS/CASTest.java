package CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ClassName:CASTest
 * Description:
 *
 * @Date:2022/5/13 14:35
 * @Author:cmt
 */
public class CASTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    static void  test1(){
        AtomicInteger atomicInteger = new AtomicInteger(2022);
        /**
         * 期望expect , 要更新成的对象update
         */
        boolean flag = atomicInteger.compareAndSet(2022, 2023);
        System.out.println(flag);
        System.out.println(atomicInteger);
    }
    static void  test2(){
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("版本stamp1 : " + stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean flag1 = atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("版本stamp2 : " + atomicStampedReference.getStamp() +" "+ flag1);

            boolean b = atomicStampedReference.compareAndSet(2, 3,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("版本stamp3 : " + atomicStampedReference.getStamp() + " " + b);
        },"a").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("线程二版本stamp4 : " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = atomicStampedReference.compareAndSet(1, 66,
                    stamp, stamp + 1);
            System.out.println(atomicStampedReference + "线程二版本stamp4 : " + atomicStampedReference.getStamp() + " " + b);

        },"b").start();
    }


}
