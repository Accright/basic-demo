package datastructure;

import java.util.Stack;

public class AppStack {
    public boolean isValid(String s) {
        Stack<String> stack = new Stack<String>();
        for (int i=0;i<s.length();i++){
            String pushN = String.valueOf(s.charAt(i));
            //将这个分别压入stack
            if (stack.empty()){
                stack.push(pushN);
            }else {
                String top = stack.peek();
                if (top.equals("(") && pushN.equals(")") || top.equals("[") && pushN.equals("]")
                        || top.equals("{") && pushN.equals("}")){
                    //如果匹配成功 出栈
                    stack.pop();
                }else {
                    stack.push(pushN);
                }
            }
        }
        //判断栈中是否有值 有的话说明没有匹配完毕
        if (stack.empty()){
            return true;
        }
        return false;
    }

    public static void main(String args[]){
        System.out.println("是否可以匹配"+new AppStack().isValid("{[}"));
    }
}
