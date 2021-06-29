package factorymode.simplefactory;

/**
 * @Program: DesignPatterns
 * @Classname: Test
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 10:33
 */
public class Test {
    public static void main(String[] args) {
        Car aCar = CarFactory.getCar("A");
        aCar.madeCar();
    }
}

