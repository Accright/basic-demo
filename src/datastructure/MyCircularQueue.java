package datastructure;

public class MyCircularQueue {
    private String[] data;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        data = new String[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(String value) {
        if (isFull() == true) {
            return false;
        }
        if (isEmpty() == true) {
            head = 0;
        }
        tail = (tail + 1) % size;
        data[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public String deQueue() {
        String value = data[head];
        if (isEmpty() == true) {
            return null;
        }
        if (head == tail) {
            head = -1;
            tail = -1;
            return value;
        }
        head = (head + 1) % size;
        return value;
    }

    /** Get the front item from the queue. */
    public String Front() {
        if (isEmpty() == true) {
            return null;
        }
        return data[head];
    }

    /** Get the last item from the queue. */
    public String Rear() {
        if (isEmpty() == true) {
            return null;
        }
        return data[tail];
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
