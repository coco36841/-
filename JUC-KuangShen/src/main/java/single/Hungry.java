package single;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:Hungry
 * Description: 饿汉式单例
 *
 * @Date:2022/5/13 13:22
 * @Author:cmt
 */
public class Hungry {

    private static Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        return hungry;
    }
}
