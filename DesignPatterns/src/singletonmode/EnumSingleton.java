package singletonmode;

/**
 * @Program: DesignPatterns
 * @Classname: EnumSingleton
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/28 12:01
 */
public enum EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}

