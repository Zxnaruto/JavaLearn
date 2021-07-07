package buildermode;

/**
 * @Program: DesignPatterns
 * @Classname: Test
 * @Author:  Zhai
 * @Description: 建造者模式就是客户只需要知道名称，以及一系列属性设置操作(和顺序无关)，就可以创建一个复杂的对象，不用关心具体的实现
 * @Date: 2021/07/07 15:32
 */
public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        Product productA = boss.builder(new Worker());
        System.out.println(productA.getName());
        System.out.println(productA.getSize());

    }
}

