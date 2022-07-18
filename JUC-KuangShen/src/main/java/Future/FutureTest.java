package Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:FutureTest
 * Description:
 *
 * @Date:2022/5/12 19:33
 * @Author:cmt
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
        });
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("异步肯定还没运行完吧");

        completableFuture.get();

        System.out.println("还不完就kill");


        CompletableFuture<Integer> Future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
            return 1024/0;
        });

        Integer integer = Future.whenComplete((t, u) -> {
            System.out.println("t: " + t);  // 正常的返回结果
            System.out.println("u: " + u);  // 错误信息
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());  //错误的返回信息
            return 233;     //错误的返回结果
        }).get();

        System.out.println(integer);
    }
}
