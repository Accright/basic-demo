package arithmetic;

public class DynamicP {
    public int getDp(int number){
        //我们用动态规划求number 基础值有1 5 11
        int f[] = new int[number+1];
        f[0] = 0;//初始化一个最小的f[0]
        int count = Integer.MAX_VALUE;
        for (int i=1;i<=number;i++){
            count = Integer.MAX_VALUE;
            if (i-1>=0){
                count = Math.min(count,f[i-1]+1);
            }
            if (i-5>=0){
                count = Math.min(count,f[i-5]+1);
            }
            if (i-11>=0){
                count = Math.min(count,f[i-11]+1);
            }
            f[i] = count;
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println("The count is >>>"+new DynamicP().getDp(15));
    }
}
