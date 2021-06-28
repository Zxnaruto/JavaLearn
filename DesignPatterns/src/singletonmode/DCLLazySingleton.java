package singletonmode;

/**
 * @Program: DesignPatterns
 * @Classname: DCLLazySingleton
 * @Author:  Zhai
 * @Description: 双重检验锁，懒汉式单例模式，相较于普通的懒汉式单例模式，该模式可以保证线程安全，同时防止指令重排, 这种情况还是会被反射破坏，即通过
 * 反射可以获取到不同的对象，为了解决这个，可以使用枚举的方式
 * @Date: 2021/06/28 11:34
 */
public class DCLLazySingleton {
    // 私有构造函数，外部不能被实例化
    private DCLLazySingleton() {}

    // volatile 防止指令重排
    private volatile static DCLLazySingleton dclLazySingleton;

    // 双重检测锁，解决并发问题
    public static DCLLazySingleton getDclLazySingleton() {
        if(dclLazySingleton == null) {
            synchronized (DCLLazySingleton.class) {
                if(dclLazySingleton == null) {
                    dclLazySingleton = new DCLLazySingleton();
                }
            }
        }
        return dclLazySingleton;
    }
}

