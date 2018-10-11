package design.factory;

public class SMSSender implements Sender {
    @Override
    public void sendMsg(String msg) {
        System.out.println("SMS >>>>>msg is"+msg);
    }
}
