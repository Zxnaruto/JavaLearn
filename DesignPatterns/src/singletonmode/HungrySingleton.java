package singletonmode;

/**
 * @Program: DesignPatterns
 * @Classname: HungrySingleton
 * @Author: Zhai
 * @Description:  饿汉式单例模式, 即一声明对象，即创建对象
 * @Date: 2021/06/28 11:20
 */
public class HungrySingleton {
    // 私有构造函数，不能被外部实例化
    private HungrySingleton(){}

    // 静态对象，声明即创建对象
    private static final HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    // 公有静态获取方法
    public static HungrySingleton getHungrySingleton() {
        return  HUNGRY_SINGLETON;
    }
}

