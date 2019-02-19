package datastructure;

import java.util.Stack;

/**
 * 用两个栈模拟一个队列 FIFO变为FILO
 */
public class StackQueue {
    /** Initialize your data structure here. */
    private Stack<Integer> lStack = new Stack<>();
    private Stack<Integer> rStack = new Stack<>();
    public StackQueue() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (!rStack.empty()){
            int temp = rStack.pop();
            lStack.push(temp);
        }
        lStack.push(x);//直接入栈就可以了
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        //应该弹出第一个元素
        while (!lStack.empty()){
            int temp = lStack.pop();
            rStack.push(temp);
        }
        return rStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        while (!lStack.empty()){
            int temp = lStack.pop();
            rStack.push(temp);
        }
        return rStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return lStack.empty() && rStack.empty();
    }

    public static void main(String[] args){
        StackQueue obj = new StackQueue();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        int param_1 = obj.pop();
        int param_2 = obj.peek();
        int param_1_1 = obj.pop();
        int param_1_2 = obj.pop();
        boolean param_3 = obj.empty();
        System.out.println("param_1"+param_1+",param_2"+param_2+","+param_1_1+","+param_1_2+",param_3"+param_3);
    }
}
