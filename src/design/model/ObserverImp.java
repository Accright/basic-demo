package design.model;

import java.util.ArrayList;
import java.util.List;

public class ObserverImp implements Observer{

    private List<ObserverVo> observerList = new ArrayList<ObserverVo>();

    @Override
    public void registerObserver(ObserverVo observerVo) {
        observerList.add(observerVo);
    }

    @Override
    public void removeObserver(ObserverVo observerVo) {
        observerList.remove(observerVo);
    }

    @Override
    public void nofityObserver(String msg) {
        if (observerList != null && observerList.size() > 0){
            for (ObserverVo observerVo : observerList){
                observerVo.update(msg);//执行所有订阅者的更新
            }
        }
    }
}
