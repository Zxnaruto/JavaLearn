package factorymode.methodfactory;

/**
 * @Program: DesignPatterns
 * @Classname: ACar
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 11:18
 */
public class ACar extends Car {
    @Override
    void getCar() {
        System.out.println("made A car");
    }
}

