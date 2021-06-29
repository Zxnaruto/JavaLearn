package factorymode.methodfactory;

/**
 * @Program: DesignPatterns
 * @Classname: Test
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 11:20
 */
public class Test {
    public static void main(String[] args) {
        Car car;
        CarFactory carFactory;
        carFactory = new ACarFactory();
        car = carFactory.getCar();
        car.getCar();
    }
}

