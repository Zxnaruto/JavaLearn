package strategymode;

/**
 * @Program: DesignPatterns
 * @Classname: SubstractOperation
 * @Author: Zhai
 * @Description: 减法策略
 * @Date: 2021/06/09 10:46
 */
public class SubtractOperation implements MyStrategy {

    @Override
    public Integer operation(int a, int b) {
        return a - b;
    }
}

