package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName:LockTest
 * Description:
 *
 * @Date:2022/5/13 21:43
 * @Author:cmt
 */
public class LockTest {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

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

        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}

class Phone2{

    Lock lock = new ReentrantLock();

    public void sms(){
        lock.lock();lock.lock();
        /**
         * lock 锁必须配对
         * 前面俩个 lock.lock();
         * 后面必须 俩个lock.unlock();
         * */
        try {
            System.out.println(Thread.currentThread().getName() + "sms");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();lock.unlock();
        }

    }

    public void call(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
