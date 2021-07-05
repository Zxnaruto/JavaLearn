package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: XiaoMiFactory
 * @Author: Zhai
 * @Description: 小米工厂生产小米app和小米手机
 * @Date: 2021/07/05 17:13
 */
public class XiaoMiFactory extends OfficeFactory {
    @Override
    public Phone phone() {
        return new XiaoMiPhone();
    }

    @Override
    public App app() {
        return new XiaoMiApp();
    }
}

