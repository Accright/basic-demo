package datastructure;

import java.util.*;

/**
 * 无向图的一道题
 * 克隆一张无向图，图中的每个节点包含一个 label （标签）和一个 neighbors （邻接点）列表 。
 * OJ的无向图序列化：
 * 节点被唯一标记。
 * 我们用 # 作为每个节点的分隔符，用 , 作为节点标签和邻接点的分隔符。
 * 例如，序列化无向图 {0,1,2#1,2#2,2}。
 * 该图总共有三个节点, 被两个分隔符  # 分为三部分。
 * 第一个节点的标签为 0，存在从节点 0 到节点 1 和节点 2 的两条边。
 * 第二个节点的标签为 1，存在从节点 1 到节点 2 的一条边。
 * 第三个节点的标签为 2，存在从节点 2 到节点 2 (本身) 的一条边，从而形成自环。
 * 我们将图形可视化如下：
 *
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 *
 * 思路： 既然要克隆图 就需要先遍历图 图的遍历有DFS和BSF两种 DFS(深度优先遍历 借助栈) BFS(广度优先遍历 借助队列)
 * 然后根据传递的节点值 递归克隆就可以了
 */
public class UndirectedGraph {
    private Map<Integer,UndirectedGraphNode> visitedMap = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        /*UndirectedGraphNode clonedGraph = null;
        Stack<UndirectedGraphNode> stack = new Stack<>();
        stack.push(node);
        clonedGraph = new UndirectedGraphNode(node.label);
        for (UndirectedGraphNode node1:node.neighbors){
            //如果已经克隆过了
            if (node1.equals(stack.peek())){

            }else{
                stack.push(node1);
                clonedGraph.neighbors.add(cloneGraph(node1));
            }
        }
        return clonedGraph;*/
        //网上的方法 使用HashMap做的
        if (null == node){
            return null;
        }
        UndirectedGraphNode cloned = visitedMap.get(node.label);
        if (null != cloned){
            return cloned;
        }
        cloned = new UndirectedGraphNode(node.label);
        visitedMap.put(node.label,node);
        for (UndirectedGraphNode nodel : node.neighbors){
            cloned.neighbors.add(cloneGraph(nodel));
        }
        return cloned;
    }

    public static void main(String[] args){
        UndirectedGraphNode n0=new UndirectedGraphNode(0);
        UndirectedGraphNode n1=new UndirectedGraphNode(1);
        UndirectedGraphNode n2=new UndirectedGraphNode(2);
        n0.neighbors.add(n1);
        n0.neighbors.add(n2);
        n1.neighbors.add(n0);
        n1.neighbors.add(n2);
        n2.neighbors.add(n2);
        UndirectedGraphNode cloned = new UndirectedGraph().cloneGraph(n0);
        System.out.println("n0 is"+n0+",cloned is"+cloned+",equals is"+n0.equals(cloned));
    }
}
/**
 * 定义一个无向图类
 */
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
};
