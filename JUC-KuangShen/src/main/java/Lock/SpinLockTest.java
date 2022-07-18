package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ClassName:SpinLockTest
 * Description: 乐观原子类实现Lock自旋锁
 *
 * @Date:2022/5/13 21:58
 * @Author:cmt
 */
public class SpinLockTest {

    // CAS
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> mylock");

        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myUnLock");
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        /**
         * 结果T2一定会等待 T1释放完锁再释放锁
         * 因为相对于获取的是同一把锁，
         * */
        SpinLockTest lock = new SpinLockTest();
        new Thread(()->{
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "T1").start();

        new Thread(()->{
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "T2").start();
    }
}
