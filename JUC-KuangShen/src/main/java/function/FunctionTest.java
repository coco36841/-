package function;

import java.util.function.Function;

/**
 * ClassName:FunctionTest
 * Description: 输入一个参数返回另一个参数
 *
 * @Date:2022/5/11 17:02
 * @Author:cmt
 */
public class FunctionTest {
    public static void main(String[] args) {
        // 普通写法
//        Function<String,String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };
        // 函数式写法
        Function<String,String> function = (s) -> {return s;};
        System.out.println(function.apply("hello"));

    }
}
