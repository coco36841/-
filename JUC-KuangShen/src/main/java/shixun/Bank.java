package shixun;

/**
 * ClassName:Bank
 * Description:
 *
 * @Date:2022/6/24 9:37
 * @Author:cmt
 */
public class Bank {

    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            // TODO Auto-generated method stub
            return 20;
        }
    };
    // 存钱
    public void addMoney(int money) {
        count.set(count.get()+money);
        System.out.println(System.currentTimeMillis() + "存进：" + money);
    }
    // 取钱
    public void subMoney(int money) {
        if (count.get() - money < 0) {
            System.out.println("余额不足");
            return;
        }
        count.set(count.get()- money);
        System.out.println(+System.currentTimeMillis() + "取出：" + money);
    }
    // 查询
    public void lookMoney() {
        System.out.println("账户余额：" + count.get());
    }
}
