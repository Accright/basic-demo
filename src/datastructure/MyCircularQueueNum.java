package datastructure;

class MyCircularQueueNum {

    //数据 队列实际存储的数据
    private int[] dataList;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueueNum(int k) {
        dataList = new int[k];
        head = -1;
        tail = -1;
        size = k;

    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        //入队操作 应该插入队尾
        if (isEmpty()){
            head = 0;
        }
        if (isFull()){
            return false;
        }
        tail = (tail + 1) % size;
        dataList[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        //出队操作 应该从队首出队
        if (isEmpty()){
            return false;
        }
        //如果队列中首尾为一个元素 只有一个元素 直接置空队首和队尾
        if (head == tail){
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return dataList[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        return dataList[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }
}

/**
 * Your MyCircularQueueNum object will be instantiated and called as such:
 * MyCircularQueueNum obj = new MyCircularQueueNum(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
