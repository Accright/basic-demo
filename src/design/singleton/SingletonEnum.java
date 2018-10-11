package design.singleton;

public enum SingletonEnum {
    INSTANCE;
    public void doSomething(){
        System.out.println(">>>>>>do Something!");
    }

    public static void main(String[] args){
        SingletonEnum.INSTANCE.doSomething();
    }
}
