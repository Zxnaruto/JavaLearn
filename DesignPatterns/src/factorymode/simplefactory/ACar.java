package factorymode.simplefactory;

/**
 * @Program: DesignPatterns
 * @Classname: ACar
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/06/29 10:29
 */
public class ACar implements Car{

    @Override
    public void madeCar() {
        System.out.println("made A car");
    }
}

