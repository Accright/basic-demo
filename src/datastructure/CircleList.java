package datastructure;

/**
 * 判断链表中是否有环
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int x,ListNode next) {
        this.val = x;
        this.next = next;
    }
}
public class CircleList {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        //用两个指针 快慢指针解决
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        ListNode node1 = new ListNode(2,null);
        ListNode node = new ListNode(1,node1);
        boolean hasCycle = new CircleList().hasCycle(node);
        System.out.println("hasCycle is +"+hasCycle);
    }
}
