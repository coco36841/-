package Callable;

import jdk.nashorn.internal.ir.FunctionCall;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName:CallableTest
 * Description:
 *
 * @Date:2022/5/11 12:06
 * @Author:cmt
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread().start();

        MyCallable mythread = new MyCallable();
        FutureTask futureTask = new FutureTask(mythread);
        new Thread(futureTask,"A").start();
        // 有缓存，只输出一次
        new Thread(futureTask,"B").start();
        new Thread(futureTask,"C").start();
        //简化
        new Thread(new FutureTask(new MyCallable()),"D").start();

        Integer o = (Integer) futureTask.get();
        System.out.println("线程结果返回： "+o);
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}