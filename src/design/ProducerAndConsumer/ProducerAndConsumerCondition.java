package design.ProducerAndConsumer;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型 使用Lock类加锁  Condition进行线程调度  注意：一定要用finally unlock对象锁
 */
public class ProducerAndConsumerCondition {
    private int size = 100;//缓存池大小
    private PriorityQueue<String> queue = new PriorityQueue<String>(size);

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args){
        ProducerAndConsumerCondition p = new ProducerAndConsumerCondition();
        Thread producer  = p.new Producer();
        Thread consumer = p.new Consumer();

        producer.start();
        consumer.start();
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce(){
            while (true){
                lock.lock();//加锁
                try{
                    while (queue.size() == size){
                        try {
                            System.out.println(">>>>>>>队列已满 生产线程停止 交出对象锁");
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            //notEmpty.signal();
                        }
                    }
                    queue.offer(">>>>>>>");
                    notEmpty.signal();//唤醒消费者线程
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(size-queue.size()));
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consumer();
        }

        private void consumer(){
            while (true){
                lock.lock();
                try{
                    while (queue.size() == 0){
                        try {
                            System.out.println("消费队列为空  暂停消费线程 交出对象锁");
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            //notFull.signal();
                        }
                    }
                    queue.poll();
                    notFull.signal();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
