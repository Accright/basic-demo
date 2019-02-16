package arithmetic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 四平方定理 任何一个正整数都可以表示为为4个数的平方和  ---我怎么没记得数学中学过
 * 其实这个题会有各种解法
 * 1 贪心算法 就是先用最大的尝试 如果可以的话 求出最优解 然后再继续求剩下部分的最优解 但是本题不适用 12 -> 4 4 4 而不是 9 1 1 1
 * 1-1 动态规划 用一个数组存每个数所需要的次数 其结果为减去相邻最大平方数(1)+减去之后的最大平方数
 * 2 使用四平方定理 满足四数平方和定理的数n（这里要满足由四个数构成，小于四个不行），肯定除以8余7
 * 3 使用图的最短路径求解 即图的广度遍历
 */
public class DFSNumSquare {
    /**
     * 使用动态规划实现一下
     * @return
     */
    public int numSquaer(int n){
        int count[] = new int[n+1];
        Arrays.fill(count,100000000);//默认为超级多次
        count[0] = 0;//初始化0的次数为0
        for (int i=0;i<=n;i++){
            //从1开始尝试 使其减去相邻的最大平方数+1
            int j = 1;
            while(i-j*j>=0){
                count[i] = Math.min(count[i],count[i-j*j]+1);
                j++;
            }
        }
        return count[n];
    }

    /**
     * 另一种动态规划的思路
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        for(int i = 1; i <= n; i++) {
            int sqr = (int)Math.sqrt(i);
            if(sqr * sqr == i) dp[i] = 1;  //如果 i 本身是个平方数，就将 dp[i] 置1
            else {
                for(int j = 1; j <= i/2; j++) {
                    dp[i] = Math.min(dp[j] + dp[i-j], dp[i]);  //从0开始遍历所有和为 i 的 dp，使得 dp[i]取最小
                }
            }
        }
        return dp[n];
    }

    public int numSquare(int n){
        //如果可以除以2的平方 不断除以下去
        while (n%4 == 0){
            n = n/4;
        }
        if (n%8 == 7)
            return 4;
        //对所有的数排除 找出一个的
        for (int a=0;a*a<=n;a++){
            if (a*a == n){
                return 1;
            }
        }
        //2步的循环找出
        for (int a=0;a*a<=n;a++){
            int b = (int)Math.sqrt(n-(a*a));
            if (a*a+b*b == n && a>=0&&b>=0){
                return 2;
            }
        }
        //最后是剩余3步的
        return 3;
    }

    /**
     * 用BFS尝试一下 最少 化解为寻找最短路径问题 图的广度遍历
     * @param n
     * @return
     */
    public int dfsNumSquare(int n){
         Queue<Integer[]> queue = new LinkedList<>();
         queue.offer(new Integer[] { 0, 0 });// 保存当前元素和已经用了的步数
         boolean[] visited = new boolean[n + 1];
         visited[0] = true;
         //bfs
         while (!queue.isEmpty()) {
             Integer[] cur = queue.poll();
             int num = cur[0];
             int step = cur[1];
             int i = 1;
             int tNum = num + i * i;
             while (tNum <= n) {//从 0->n 搜索不重复的和，一旦重复说明出现过，例如1+9=9+1=10，就不再添加
                 if (tNum == n)
                     return step + 1;
                 if (!visited[tNum]) {
                     queue.offer(new Integer[] { tNum, step + 1 });
                     visited[tNum] = true;
                 }
                 i += 1;
                 tNum = num + i * i;
             }
         }
         return 0;
    }

    public static void main(String[] args){
        System.out.println("最小平方数是->"+new DFSNumSquare().numSquaer(102));
        System.out.println("最小平方数是->"+new DFSNumSquare().numSquare(102));
        System.out.println("最小平方数是->"+new DFSNumSquare().dfsNumSquare(102));
    }
}
