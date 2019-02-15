package fileop;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class FileOP {

    public static void main(String[] args) throws Exception {

        //字节流
        //byteOutStream();


        //字符流 (输出流中含有中文时使用字符流)
        for (int i = 0;i<10;i++){
            charOutStream("嘿哈");
        }

    }

    public static void charOutStream(String str) throws Exception{
        // 1：利用File类找到要操作的对象
        File file = new File("D:" + File.separator + "demo" + File.separator + "test.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        //2：准备输出流
        Writer out = new FileWriter(file,true);
        out.write(str+"\n");
        out.close();

    }

}
