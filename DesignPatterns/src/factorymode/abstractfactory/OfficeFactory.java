package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: OfficeFactory
 * @Author: Zhai
 * @Description:  总的公司抽象类，具体定义公司的行为，比如生产手机
 * @Date: 2021/07/05 17:00
 */
public abstract class OfficeFactory {
    // 生产手机职能
    public abstract Phone phone();
    // 生产App职能
    public abstract App app();
}

