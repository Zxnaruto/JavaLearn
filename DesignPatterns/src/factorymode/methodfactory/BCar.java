package factorymode.methodfactory;

/**
 * @Program: DesignPatterns
 * @Classname: BCar
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 11:18
 */
public class BCar extends Car {

    @Override
    void getCar() {
        System.out.println("made B car");
    }
}

