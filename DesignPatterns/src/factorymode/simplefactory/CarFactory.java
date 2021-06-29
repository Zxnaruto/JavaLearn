package factorymode.simplefactory;

/**
 * @Program: DesignPatterns
 * @Classname: CarFactory
 * @Author: Abner Zhai
 * @Description: 简单工厂方法，即静态工厂方法，通过创建公共接口方法（公共的方法），使得不同类去实现该接口并实现不同的接口方法内容，通过静态工厂方法进行连接，
 * 想创建什么类型的对象，根据type获取即可，使用者不用知道具体实现，只用知道相要获取什么对象即可
 * @Date: 2021/06/29 10:30
 */
public class CarFactory {
    public static Car getCar(String carType) {
        if(carType.equals("A")) {
            return new ACar();
        } else if(carType.equals("B")){
            return new BCar();
        } else {
            return new CCar();
        }
    }
}

