package design.model;

public class ObserverVoImpl implements ObserverVo {

    //构造函数
    public ObserverVoImpl(Observer observer){
        observer.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println("收到的通知为+++"+msg);
    }
}
