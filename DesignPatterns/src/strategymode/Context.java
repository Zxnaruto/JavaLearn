package strategymode;

/**
 * @Program: DesignPatterns
 * @Classname: Context
 * @Author: Zhai
 * @Description: 行为会随对象改变的具体实现--> 策略模式具体使用,不同的策略进行不同的初始化，通过context
 * @Date: 2021/06/09 11:04
 */
public class Context {
    private MyStrategy myStrategy;

    public void setMyStrategy(MyStrategy myStrategy) {
        this.myStrategy = myStrategy;
    }

    public int contextExec(int a, int b) {
        return myStrategy.operation(a, b);
    }
}

