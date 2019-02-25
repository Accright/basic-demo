package question;

import java.util.Stack;

public class StringDecode {
    public String decodeString(String s) {
        /*StringBuilder reString = new StringBuilder();
        Stack<String> stack = new Stack<>();
        StringBuilder iStringB = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        boolean sFlag = false;
        int numR = 0;
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                iStringB.append(c);
            }else {
                if (!"".equals(iStringB.toString())){
                    numR = Integer.valueOf(iStringB.toString());
                }
                iStringB = new StringBuilder();
                //有[这个符号入栈
                if (c == '['){
                    *//*StringBuilder temp = new StringBuilder();
                    for (int j=i+1;j<s.length();j++){
                        char n = s.charAt(j);
                        //到该符号入栈结束 然后出栈
                        if (n != ']' && n != ']' && !Character.isDigit(n)){
                            temp.append(n);
                        }else {
                            break;
                        }
                    }*//*
                    // 开始入栈 给个标识
                    sFlag = true;
                }else if (c == ']'){
                    //到]符号开始出栈
                    if (!stack.empty()){
                        String string = stack.pop();
                        //开始生成字符串
                        for (int init = 0;init<numR;init++){
                            reString.append(string);
                        }
                    }
                }else {
                    if (!Character.isDigit(c)){
                        temp.append(c);
                    }else {
                        sFlag = false;
                    }
                }
            }
        }
        return reString.toString();*/
        String res = "";
        // 记录'['之前的数字
        Stack<Integer> countStack = new Stack<>();
        // 记录'['之前的运算结果
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        int curNum = 0;
        while (idx < s.length())

        {
            char ch = s.charAt(idx);
            if (Character.isDigit(ch)) {
                while (Character.isDigit(s.charAt(idx))){
                    curNum = 10 * curNum
                            + (s.charAt(idx++) - '0');
                }
            } else if (ch == '[') {
                resStack.push(res);
                res = "";// 注意
                countStack.push(curNum);
                curNum = 0;// 注意
                idx++;
                // 取出计算结果，和数字
            } else if (ch == ']') {
                StringBuilder temp =
                        new StringBuilder(resStack.pop());

                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
                // 字母
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public static void main(String[] args){
        String x = new StringDecode().decodeString("3[a]2[bc]");
        System.out.println("String is "+x);
    }
}
