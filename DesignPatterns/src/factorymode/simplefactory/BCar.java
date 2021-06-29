package factorymode.simplefactory;

/**
 * @Program: DesignPatterns
 * @Classname: BCar
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 10:29
 */
public class BCar implements Car {

    @Override
    public void madeCar() {
        System.out.println("made B car");
    }
}

