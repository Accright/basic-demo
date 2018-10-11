package design.factory;

public class Main {
    public static void main(String[] args){
        System.out.println(">>>>>>factory excute");
        Sender mailSender = Factory.produceMailSender();
        Sender smsSender = Factory.produceSmsSender();
        mailSender.sendMsg("mail msg");
        smsSender.sendMsg("sms msg");
    }
}
