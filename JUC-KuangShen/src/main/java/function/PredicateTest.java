package function;

import java.util.function.Predicate;

/**
 * ClassName:PredicateTest
 * Description: 输入一个参数返回一个Boolean
 *
 * @Date:2022/5/11 17:07
 * @Author:cmt
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };
        System.out.println(predicate.test(""));
    }
}
