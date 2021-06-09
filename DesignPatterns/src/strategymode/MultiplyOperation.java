package strategymode;

/**
 * @Program: DesignPatterns
 * @Classname: MultiplyOperation
 * @Author: Zhai
 * @Description: 乘法策略
 * @Date: 2021/06/09 10:48
 */
public class MultiplyOperation implements MyStrategy{
    @Override
    public Integer operation(int a, int b) {
        return a * b;
    }
}

