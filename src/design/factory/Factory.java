package design.factory;

public class Factory {
    public static Sender produceSmsSender(){
        return new SMSSender();
    }
    public static Sender produceMailSender(){
        return new MailSender();
    }
}
