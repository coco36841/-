package single;

/**
 * ClassName:Holder
 * Description: 静态内部类
 *
 * @Date:2022/5/13 13:39
 * @Author:cmt
 */
public class Holder {

    private Holder(){

    }

    public static Holder getInstance(){
        return InnerClass.holder;
    }

    public static class InnerClass {
        public static final Holder holder = new Holder();
    }
}
