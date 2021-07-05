package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: XiaoMiApp
 * @Author: Zhai
 * @Description: 小米app定义
 * @Date: 2021/07/05 17:12
 */
public class XiaoMiApp implements App {
    @Override
    public String app() {
        return "xiaomi app";
    }
}

