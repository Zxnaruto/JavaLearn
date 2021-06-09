package strategymode;

/**
 * @Program: DesignPatterns
 * @Classname: DivisionOperation
 * @Author: Zhai
 * @Description: 除法策略
 * @Date: 2021/06/09 10:49
 */
public class DivisionOperation implements MyStrategy{
    @Override
    public Integer operation(int a, int b) {
        return a / b;
    }
}

