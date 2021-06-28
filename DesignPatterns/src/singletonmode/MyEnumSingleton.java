package singletonmode;

/**
 * @Program: DesignPatterns
 * @Classname: MyEnumSingleton
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/28 12:07
 */
public class MyEnumSingleton {
    private MyEnumSingleton(){}

    // 枚举类只会初始化一次
    private enum SingletonEnum{
        INSTANCE;
        private MyEnumSingleton myEnumSingleton;
        SingletonEnum() {
            myEnumSingleton = new MyEnumSingleton();
        }
        public MyEnumSingleton getMyEnumSingleton() {
            return myEnumSingleton;
        }
    }

    public static MyEnumSingleton getInstance(){
        return SingletonEnum.INSTANCE.getMyEnumSingleton();
    }
}

