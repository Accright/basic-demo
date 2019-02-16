package datastructure;

import java.util.Stack;

public class MinStack {
    //最小栈 可以维持两个栈  一个栈只存最小值
    private Stack<Integer> pStack;
    private Stack<Integer> mStack;//最小值栈
    public MinStack() {
        pStack = new Stack<>();
        mStack = new Stack<>();
    }

    public void push(int x) {
        pStack.push(x);
        if (mStack.empty() || mStack.peek() > x){
            //mStack.pop();//弹出栈
            mStack.push(x);//压入栈
        }
    }

    public void pop() {
        /*不能直接这样写 peek出来的是对象 == 不能判断
        if (pStack.peek() == mStack.peek()){
            mStack.pop();
        }
        pStack.pop();*/
        int x = pStack.pop();
        if (x == mStack.peek()){
            mStack.pop();
        }
    }

    public int top() {
        return pStack.peek();
    }

    public int getMin() {
        return mStack.peek();
    }

    public static void main(String[] args){
        MinStack obj = new MinStack();
        obj.push(1);
        obj.push(0);
        obj.push(2);
        obj.push(-1);
        obj.push(-2);
        int param_1 = obj.getMin();
        obj.pop();
        int param_2 = obj.top();
        int param_3 = obj.getMin();
        System.out.println("可以获取最小栈的值为"+param_1+","+param_2+","+param_3);
    }
}

