package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: XiaoMiPhone
 * @Author: Zhai
 * @Description: 小米手机定义
 * @Date: 2021/07/05 17:14
 */
public class XiaoMiPhone implements Phone{
    @Override
    public String phone() {
        return "xiao mi phone";
    }
}

