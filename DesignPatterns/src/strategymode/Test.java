package strategymode;


/**
 * @Program: DesignPatterns
 * @Classname: Test
 * @Author: Zhai
 * @Description: 测试类
 * @Date: 2021/06/09 10:50
 */
public class Test {
    public static void main(String[] args) {
//        MyStrategy add = new AddOperation();
//        System.out.println(add.operation(4,2));
//        MyStrategy sub = new SubtractOperation();
//        System.out.println(sub.operation(4,2));
//        MyStrategy multi = new MultiplyOperation();
//        System.out.println(multi.operation(4, 2));
//        MyStrategy division = new DivisionOperation();
//        System.out.println(division.operation(4,2));

        // 需要context来防止上面那样每次使用都要new
        Context context = new Context();
        context.setMyStrategy(new AddOperation());
        System.out.println(context.contextExec(2,1));
        context.setMyStrategy(new SubtractOperation());
        System.out.println(context.contextExec(4,2));

    }
}

