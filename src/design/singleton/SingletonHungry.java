package design.singleton;


//饿汉模式
public class SingletonHungry {
    private static SingletonHungry singletonHungry = new SingletonHungry();
    public SingletonHungry getInstance(){
        return singletonHungry;
    }
}
