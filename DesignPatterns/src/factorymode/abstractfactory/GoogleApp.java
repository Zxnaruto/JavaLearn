package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: GoogleApp
 * @Author:  Zhai
 * @Description: 谷歌制作自己app的定义
 * @Date: 2021/07/05 17:07
 */
public class GoogleApp implements  App{
    @Override
    public String app() {
        return "google App";
    }
}

