package buildermode;

/**
 * @Program: DesignPatterns
 * @Classname: Worker
 * @Author: Zhai
 * @Description: 继承Builder类，进行具体产品的实现
 * @Date: 2021/07/07 15:27
 */
public class Worker extends Builder{
    private Product product;

    public Worker() {
        product = new Product();
    }
    @Override
    void productA() {
        product.setName("A");
        product.setSize(10);
        System.out.println("bulid A");
    }

    @Override
    Product getProduct() {
        return product;
    }
}

