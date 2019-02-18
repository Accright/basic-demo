package question;

import java.util.Arrays;

public class QStack {
    /**
     * 一个关于栈的有趣的问题
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。
     */
    private int[] dailyTemperatures(int[] T){
        int[] days = new int[T.length];
        for (int i=0;i<T.length;i++){
            for (int j = i+1;j<T.length;j++){
                if (T[i] < T[j]){
                    days[i] = j-i;
                    break;
                }else {
                    days[i] = 0;
                }

            }
        }
        return days;
    }

    public static void main(String args[]){
        System.out.println("days is"+Arrays.toString(new QStack().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }
}
