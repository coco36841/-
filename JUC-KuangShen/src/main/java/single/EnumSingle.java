package single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassName:EnumSingle
 * Description:
 *
 * @Date:2022/5/13 13:56
 * @Author:cmt
 */
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingle instance1 = EnumSingle.INSTANCE;

        Constructor<EnumSingle> declaredConstructor =
                EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);

        EnumSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);

        /**
         * javap -p 反编译不行，发现有无参构造，但是其实报错
         * Exception in thread "main" java.lang.NoSuchMethodException: single.EnumSingle.<init>()
         * 已经告诉我们没有没有无参。
         *
         * 于是用了 jad.exe 来反编译，发现没有无参构造，只有有参构造
         * private EnumSingle (String s, int i)
         *
         * 于是我们反射用有参  EnumSingle.class.getDeclaredConstructor(String.class, int.class);
         * 才发现了源码里面的终极报错：Cannot reflectively create enum objects
         * 不让用反射，反射enum类
         * 可以直接在 newInstance 源码方法里看到
         * if ((clazz.getModifiers() & Modifier.ENUM) != 0)
         *             throw new IllegalArgumentException("Cannot reflectively create enum objects");
         * */
    }
}
