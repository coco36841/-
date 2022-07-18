package single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassName:LazyMan
 * Description: 懒汉式单例
 *
 * @Date:2022/5/13 13:24
 * @Author:cmt
 */
public class LazyMan {

    private volatile static LazyMan lazyMan;

    private LazyMan(){
        // 防止一次普通new对象，一次反射的破坏
        // 无法防止两次反射破坏。
        synchronized (LazyMan.class) {
            if (lazyMan != null) {
                throw new RuntimeException("不要试图用反射破坏");
            }
        }
        System.out.println(Thread.currentThread().getName() + "ok");
    }
    // 双重检测锁，懒汉单例，DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                    /**
                     * lazyMan = new LazyMan();不是一个原子操作
                     * 1. 分配内存空间
                     * 2. 执行构造方法
                     * 3. 把这个对象指向这个空间
                     *
                     * 正常单线程情况下，就算 321 在cpu里是可以做到的，所以可能指令重排
                     *
                     * 但是多线程情况下，如果第一个线程先 （3 指向这个空间），
                     * 第二个线程就以为已经new好了，直接return，结果返回了一个虚无的对象，产生问题
                     *
                     * 加 volatile，防止指令重排
                     * */
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LazyMan lazyMan1 = getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor();

        declaredConstructor.setAccessible(true);
        LazyMan lazyMan2 = declaredConstructor.newInstance();
        System.out.println(lazyMan1);
        System.out.println(lazyMan2);
    }
}
