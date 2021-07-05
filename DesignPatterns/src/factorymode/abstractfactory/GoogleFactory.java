package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: GoogleFactory
 * @Author: Zhai
 * @Description: 谷歌工厂生产谷歌app, 谷歌工厂生产谷歌手机
 * @Date: 2021/07/05 17:09
 */
public class GoogleFactory  extends OfficeFactory{
    @Override
    public Phone phone() {
        return new GooglePhone();
    }

    @Override
    public App app() {
        return new GoogleApp();
    }
}

