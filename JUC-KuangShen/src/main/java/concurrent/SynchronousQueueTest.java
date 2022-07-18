package concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * ClassName:SynchronousQueueTest
 * Description: 同步队列
 *
 * @Date:2022/5/11 15:37
 * @Author:cmt
 */
// 不存储元素，一旦put一个，就必须取出去，否则不能put
public class SynchronousQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue();

        new Thread(()->{

        }).start();

    }
}
