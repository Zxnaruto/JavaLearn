package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: GooglePhone
 * @Author: Zhai
 * @Description:  谷歌手机的定义
 * @Date: 2021/07/05 17:10
 */
public class GooglePhone implements Phone{
    @Override
    public String phone() {
        return "Google phone";
    }
}

