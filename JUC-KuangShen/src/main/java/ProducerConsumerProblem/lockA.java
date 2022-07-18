package ProducerConsumerProblem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName:lockA
 * Description:
 *
 * @Date:2022/5/11 9:03
 * @Author:cmt
 */

public class lockA {
    public static void main(String[] args) {
        // Data1基础的生产者消费者
        Data1 data = new Data1();
        // Data2升级版 A完了B,B完了C,C完了D....
        // 就是多个Condition, 使用conditionX.signal();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"D").start();
    }


}

class Data1{

    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void increment(){
        lock.lock();
        try {
            // while 防止虚假唤醒
            while (num > 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + " -> " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();

        try {
            // while 防止虚假唤醒
            while (num <= 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + " -> " + num);
            condition.signalAll();  //不是 notifyALL
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}