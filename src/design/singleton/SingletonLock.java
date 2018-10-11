package design.singleton;

public class SingletonLock {
    private static volatile SingletonLock singletonLock = null;

    //锁定类的静态方法
    public static synchronized SingletonLock getInstance(){
        return singletonLock;
    }
}
