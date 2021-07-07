package buildermode;

/**
 * @Program: DesignPatterns
 * @Classname: Builder
 * @Author: Zhai
 * @Description: builder抽象类，声明产品的创建方法和获取方法
 * @Date: 2021/07/07 15:25
 */
public abstract class Builder {
    abstract void productA();
    abstract Product getProduct();
}

