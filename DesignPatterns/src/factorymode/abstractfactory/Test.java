package factorymode.abstractfactory;

/**
 * @Program: DesignPatterns
 * @Classname: Test
 * @Author: Zhai
 * @Description: 抽象工厂测试
 * @Date: 2021/07/05 17:15
 */
public class Test {
    public static void main(String[] args) {
        OfficeFactory google = new GoogleFactory();
        System.out.println(google.app().app());
        OfficeFactory xiaomi = new XiaoMiFactory();
        System.out.println(xiaomi.phone().phone());
    }
}

