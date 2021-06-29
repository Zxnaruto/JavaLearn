package factorymode.simplefactory;

/**
 * @Program: DesignPatterns
 * @Classname: CCar
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 10:30
 */
public class CCar implements Car {

    @Override
    public void madeCar() {
        System.out.println("made C car");
    }
}

