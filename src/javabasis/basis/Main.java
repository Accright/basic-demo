package javabasis.basis;

import design.authority.Base;

public class Main {
    public static void main(String[] args){
        Order order = new Order();
        Test test = new Test();
        test.test();
    }
}

class Test extends Base{
    @Override
    protected void test() {
        super.test();
    }
}