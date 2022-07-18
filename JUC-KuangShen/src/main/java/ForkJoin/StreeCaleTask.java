package ForkJoin;

import java.util.OptionalLong;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * ClassName:StreeCaleTask
 * Description:
 *
 * @Date:2022/5/12 16:31
 * @Author:cmt
 */
public class StreeCaleTask {

    static Long sta = 0L;
    static Long en = 10_0000_0000L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();  //500000000500000000  6355
//        test2();  //500000000500000000  5228
//        test3();  //500000000500000000 335
    }

    public static void test1() {
        Long sum = 0L;
        Long start = System.currentTimeMillis();
        for (Long i = sta; i <= en; i++) {
            sum += i;
        }
        Long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end - start);
    }
    public static void test2() throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();

        ForkJoinTask<Long> forkJoinTest = new ForkJoinTest(sta, en);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTest);
        Long sum = submit.get();

        Long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end - start);
    }
    public static void test3() {
        long start = System.currentTimeMillis();

        Long sum = LongStream.rangeClosed(sta, en).parallel().reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end - start);
    }

}
