package question;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 逆波兰表示法 就是后缀表达式的求值 初步想法 用两个栈就可以实现 大学的时候是实现过的
 */
public class QRPN {
    public int evalRPN(String[] tokens) {
        Stack<String> lamStack = new Stack();//表达式stack
        Stack<String> operStack = new Stack<>();//操作符表达式
        //所有字符入栈 需要反着入栈
        for (int i=tokens.length-1;i >=0;i--){
            String token = tokens[i];
            lamStack.push(token);
        }
        //lamStack.
        //开始出栈操作
        while (!lamStack.empty()){
            String lam = lamStack.pop();
            //判断 如果是操作数 入栈 否则出栈两个数运算 然后继续压栈
            if (isNum(lam)){
                operStack.push(lam);
            }else {
                //直接出栈两个数 肯定要出栈两个 不然就不是后序遍历了
                String numL = operStack.pop();
                String numR = operStack.pop();
                int result = 0;
                if (lam.equals("+")){
                    result = Integer.valueOf(numR) + Integer.valueOf(numL);
                }else if (lam.equals("-")){
                    result = Integer.valueOf(numR) - Integer.valueOf(numL);
                }else if (lam.equals("*")){
                    result = Integer.valueOf(numR) * Integer.valueOf(numL);
                }else if (lam.equals("/")){
                    result = Integer.valueOf(numR) / Integer.valueOf(numL);
                }
                String resultStr = String.valueOf(result);
                operStack.push(resultStr);
            }
        }
        return Integer.valueOf(operStack.peek());
    }

    //使用正则判断是否为数字
    public boolean isNum(String numStr){
        //Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        //return pattern.matcher(numStr).matches();
        String str = numStr;
        if(numStr.contains("-") && numStr.length() >= 2){
            str = numStr.split("-")[1];
        }
        for(int i=str.length(); --i>=0;){
            char c=str.charAt(i);
            if (c < 48 || c > 57)
                return false;
        }
        return true;
    }

    public static void main(String args[]){
        System.out.println("表达式求值为->"+new QRPN().evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
