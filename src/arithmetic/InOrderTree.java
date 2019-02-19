package arithmetic;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历 是深度遍历DFS的一种 可以用栈辅助
 * 用递归实现一下
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 */
public class InOrderTree {
    /*public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root,list);
        return list;
    }
*/
    //递归实现的中序遍历 先左再中然后右
    public void inOrder(TreeNode node,List list){
        if (null == node){
            return;
        }
        //先遍历左节点 再遍历中节点 最后遍历右节点
        inOrder(node.left,list);
        list.add(node.val);
        inOrder(node.right,list);
    }

    //用栈辅助的遍历
    //再出栈一个值之后 记录该值 然后该值就可以废弃 直接访问它的右节点就可以了 因为入栈的值都是左节点的值
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> nodeStack = new Stack<>();
        //遍历完左子树之后 开始输出
        while (!nodeStack.empty() || null != root){
            while (root != null || nodeStack.size() != 0) {
                if (root != null) {
                    nodeStack.push(root);
                    root = root.left;
                } else {
                    root = nodeStack.pop();
                    list.add(root.val);
                    root = root.right;
                }
            }
            //list.add(node.val);
            //TreeNode inNode = nodeStack.pop();
            //TreeNode rNode = inNode.right;
        }
        return list;
    }

    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = null;
        TreeNode children = new TreeNode(2);
        TreeNode grandChild = new TreeNode(3);
        treeNode.right = children;
        children.left = grandChild;
        children.right = null;
        List<Integer> list = new InOrderTree().inorderTraversal(treeNode);
        for (int x : list){
            System.out.println(">>>>>>>>>>>>>>"+x);
        }
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
