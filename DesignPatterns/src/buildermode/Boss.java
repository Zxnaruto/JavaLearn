package buildermode;

/**
 * @Program: DesignPatterns
 * @Classname: Boss
 * @Author: Zhai
 * @Description: 指挥工人去建造产品
 * @Date: 2021/07/07 15:31
 */
public class Boss {
    public Product builder(Builder builder) {
        builder.productA();
        return builder.getProduct();
    }
}

