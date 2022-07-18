package ForkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * ClassName:ForkJoinTest
 * Description:
 *
 * @Date:2022/5/12 10:57
 * @Author:cmt
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private Long temp = 10000L;  //临界值
    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long sum = 0L;
        if ((end - start) <= temp) {
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        Long mid = (start + end) / 2;
        ForkJoinTest task1 = new ForkJoinTest(start,mid);
        task1.fork();   // 把任务压入线程队列
        ForkJoinTest task2 = new ForkJoinTest(mid + 1, end);
        task2.fork();

        return task1.join() + task2.join();
    }
}
