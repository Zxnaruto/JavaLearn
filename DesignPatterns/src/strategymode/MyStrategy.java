package strategymode;
/*
* 策略方法接口
* */
public interface MyStrategy {
    /*
    * 对a,b进行加减乘除操作
    * */
    Integer operation(int a, int b);
}
