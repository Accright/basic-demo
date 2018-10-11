package design.model;

public interface Observer {
    //注册观察者（订阅者）
    public void registerObserver(ObserverVo observerVo);
    //删除观察者
    public void removeObserver(ObserverVo observerVo);
    //发送观察者消息
    public void nofityObserver(String msg);
}
