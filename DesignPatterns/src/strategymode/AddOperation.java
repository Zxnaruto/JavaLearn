package strategymode;

/**
 * @Program: DesignPatterns
 * @Classname: AddOperation
 * @Author: Zhai
 * @Description: 加法策略
 * @Date: 2021/06/09 10:45
 */
public class AddOperation implements MyStrategy{
    @Override
    public Integer operation(int a, int b) {
        return a + b;
    }
}

