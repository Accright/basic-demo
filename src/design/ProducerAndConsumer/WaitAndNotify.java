package design.ProducerAndConsumer;

public class WaitAndNotify {
    private static Object object = new Object();//测试锁定的对象
    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        //启动thread1
        thread1.start();

        //睡眠0.2s
        Thread.sleep(200);

        thread2.start();

    }

    private static class Thread1 extends Thread{
        @Override
        public void run(){
            //锁定测试对象
            synchronized (object){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(">>>>>线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }

    private static class Thread2 extends Thread{
        @Override
        public void run(){
            synchronized (object){
                object.notify();
                System.out.println(">>>>>>>线程"+Thread.currentThread().getName()+"调用了notify");
                System.out.println(">>>>>>>>线程"+Thread.currentThread().getName()+"释放了锁");
            }
        }
    }
}
