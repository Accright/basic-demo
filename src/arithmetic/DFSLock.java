package arithmetic;

import java.util.*;

/**
 * 开密码锁问题 可以转换为遍历图的问题
 * 例如在状态"0000"分别向上或者向下转动四个转轮中的一个，
 * 可以得到如下8个状态："0001"、"0009"、"0010"、"0090"、"0100"、"0900"、"1000"和"9000"。
 * 那么图中顶点"0000"就有8条边分别和这8个状态对应的顶点相连。
 */
public class DFSLock {
    public int openLock(String[] ends,String target){
        int steps = 0;
        //转换为end状态集
        Set<String> endSet = new HashSet<String>(Arrays.asList(ends));
        //访问过的状态集
        Set<String> visited = new HashSet<String>();
        //初始化状态
        String init = "0000";
        if (endSet.contains(init) || endSet.contains(target)){
            return -1;
        }
        //定义两个队列 分别存储访问到的节点和下一步要访问的节点
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        //开始广度遍历 BFS
        queue1.offer(init);
        while (!queue1.isEmpty()){
            String cur = queue1.poll();
            if (cur.equals(target)){
                return steps;
            }
            List<String> nextList = getNexts(cur);
            for (String state:nextList) {
                //可以看做预存其关联的状态点至queue2
                if (!endSet.contains(state) && !visited.contains(state)) {
                    visited.add(state);
                    queue2.offer(state);//入第二个队列 继续作为起点节点进行遍历
                }
            }
            //队列1循环完之后 需要加一步
            if (queue1.isEmpty()){
                steps++;
                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }
        return -1;
    }

    /**
     * 根据当前节点返回其关联的节点
     * @param cur
     * @return
     */
    public List<String> getNexts(String cur){
        List<String> list = new ArrayList<>();
        //循环处理每个值 使其加减之后加入队列状态中 1个节点 -> 8个状态
        for (int i =0 ;i < cur.length();i++){
            char ch = cur.charAt(i);
            //左旋之后的状态
            char newChar = ch == '0'?'9':(char)(ch-1);
            StringBuilder stringBuilder = new StringBuilder(cur);
            stringBuilder.setCharAt(i,newChar);
            list.add(stringBuilder.toString());
            //右旋之后的状态
            newChar = ch == '9'?'0':(char)(ch+1);
            stringBuilder = new StringBuilder(cur);
            stringBuilder.setCharAt(i,newChar);
            list.add(stringBuilder.toString());
        }
        return list;
    }

    public static void main(String args[]){
        long cur = new Date().getTime();
        int step = new DFSLock().openLock(new String[]{"0201","0101","0102","1212","2002"},"0202");
        long now = new Date().getTime();
        System.out.println("step is"+step+",用时为"+(now-cur)+"毫秒");
    }
}
