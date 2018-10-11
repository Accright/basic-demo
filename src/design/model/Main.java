package design.model;

public class Main {

    public static void main(String[] args) {
        System.out.println("design model excute!");
        Observer observer = new ObserverImp();
        ObserverVo observerVo1 = new ObserverVoImpl(observer);//监听当前订阅者
        observer.nofityObserver(">>>>>>发布消息啦！");
    }
}
