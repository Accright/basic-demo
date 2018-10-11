package design.singleton;

public class SingletonInnerClass {
    //通过构建内部类实现延迟加载
    public static class SingletonHodler{
        private static SingletonInnerClass singleton = new SingletonInnerClass();
    }
    public static SingletonInnerClass getInstance(){
        return SingletonHodler.singleton;
    }
}
