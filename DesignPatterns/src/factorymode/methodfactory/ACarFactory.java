package factorymode.methodfactory;

/**
 * @Program: DesignPatterns
 * @Classname: ACarFactory
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 11:19
 */
public class ACarFactory extends CarFactory {
    @Override
    Car getCar() {
        return new ACar();
    }
}

