package factorymode.methodfactory;

/**
 * @Program: DesignPatterns
 * @Classname: BCarFactory
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 11:19
 */
public class BCarFactory extends CarFactory {
    @Override
    Car getCar() {
        return new BCar();
    }
}

