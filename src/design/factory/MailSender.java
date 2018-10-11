package design.factory;

public class MailSender implements Sender {
    @Override
    public void sendMsg(String msg) {
        System.out.println("Mail send msg is >"+msg);
    }
}
