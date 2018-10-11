package design.singleton;

public class Singleton {
    public static volatile Singleton singleton = null;
    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){//双重锁 保证两个线程同时会串行执行
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
