package javabasis.basis;

public class Order {
    static {
        System.out.println(">>>>>Order Class Static Code Block");
    }

    /**
     * 构造方法
     */
    public Order(){
        System.out.println(">>>>>>>>>Order Construction Function");
    }

    private OrderBase orderBase = new OrderBase();

}
