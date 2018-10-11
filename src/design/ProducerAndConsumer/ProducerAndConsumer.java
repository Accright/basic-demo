package design.ProducerAndConsumer;

import java.util.PriorityQueue;

public class ProducerAndConsumer {
    /**
     * 基于wait和notify实现  加锁使用的synchronized
     */
    private int size = 100;//缓存池大小
    private PriorityQueue<String> queue = new PriorityQueue<String>(size);

    public static void main(String[] args){
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
        //启动生产者和消费者  必须创建实例对象 避免static中不能直接使用内部类的问题
        Thread producer = producerAndConsumer.new Producer();
        Thread consumer = producerAndConsumer.new Consumer();
        Thread consumer2 = producerAndConsumer.new Consumer2();
        producer.start();
        consumer.start();
        consumer2.start();
    }

    /**
     * 生产者线程
     */
    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        //生产者方法
        public void produce(){
            while (true){
                //队列加锁
                synchronized (queue){
                    while (queue.size() == size){
                        //队列已满 交出当前对象控制权 让消费者先消费 同时当前线程处于等待状态
                        try {
                            System.out.println("队列已满 交出当前对象控制权 让消费者先消费 同时当前线程处于等待状态");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notifyAll();
                        }
                    }
                    //进行队列生产
                    queue.offer("正在生产  当前队列size");
                    queue.notifyAll();//生产完成之后 唤醒一个消费者线程进行消费
                    System.out.println(">>>>>>>生产完毕 当前队列为"+queue.size()+"剩余空间为+"+(size-queue.size()));
                }
            }
        }
    }

    /**
     * 消费者
     */
    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        public void consume(){
            //不断轮询消费
            while (true){
                //消费时加锁
                synchronized (queue){
                    //如果队列中无数据 阻塞当前线程
                    while (queue.size() == 0){
                        try {
                            System.out.println("如果队列中无数据 交出当前对象控制权 生产者进行生产 同时当前线程处于等待状态");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //获取一个数据
                    queue.poll();
                    queue.notify();
                    System.out.println(">>>>>>>>>Consumer1  消费了一个数据,当前队列为"+queue.size()+"剩余空间为"+(size-queue.size()));
                }
            }
        }
    }

    /**
     * 消费者
     */
    class Consumer2 extends Thread{
        @Override
        public void run() {
            consume();
        }

        public void consume(){
            //不断轮询消费
            while (true){
                //消费时加锁
                synchronized (queue){
                    //如果队列中无数据 阻塞当前线程
                    while (queue.size() == 0){
                        try {
                            System.out.println("如果队列中无数据 交出当前对象控制权 生产者进行生产 同时当前线程处于等待状态");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //获取一个数据
                    queue.poll();
                    queue.notify();
                    System.out.println(">>>>>>>>>Consumer2 消费了一个数据,当前队列为"+queue.size()+"剩余空间为"+(size-queue.size()));
                }
            }
        }
    }
}

