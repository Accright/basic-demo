package datastructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 * 由LIFO变为FIFO
 */
public class QueueStack {
    private Queue<Integer> lQueue;
    private Queue<Integer> rQueue;
    /** Initialize your data structure here. */
    public QueueStack() {
        lQueue = new LinkedList<>();
        rQueue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        /*int size = rQueue.size();
        for (int i=0;i<size;i++){
            int temp = rQueue.poll();
            lQueue.offer(temp);
        }*/
        lQueue.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = lQueue.size();
        for (int i=0;i<size-1;i++){
            int temp = lQueue.poll();
            rQueue.offer(temp);
        }
        int rSize = rQueue.size();
        int x = lQueue.poll();
        for (int i=0;i<rSize;i++){
            int temp = rQueue.poll();
            lQueue.offer(temp);
        }
        return x;
    }

    /** Get the top element. */
    public int top() {
        int size = lQueue.size();
        for (int i=0;i<size-1;i++){
            int temp = lQueue.poll();
            rQueue.offer(temp);
        }
        int x = lQueue.poll();
        rQueue.offer(x);
        int rSize = rQueue.size();
        for (int i=0;i<rSize;i++){
            int temp = rQueue.poll();
            lQueue.offer(temp);
        }
        return x;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return lQueue.isEmpty()&&rQueue.isEmpty();
    }

    public static void main(String[] args){
        QueueStack obj = new QueueStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        int param_1 = obj.pop();
        int param_2 = obj.top();
        int param_1_1 = obj.pop();
        int param_1_2 = obj.pop();
        boolean param_3 = obj.empty();
        System.out.println("param_1,"+param_1+",param_2,"+param_2+","+param_1_1+","+param_1_2+",param_3,"+param_3);
    }
}
