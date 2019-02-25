package datastructure;

/**
 * 判断两个链表是否相交
 */
public class IntersectionList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //如果有一个空链表 肯定不会相交
        if (headA == null || headB == null){
            return null;
        }
        //相交链表右侧肯定是相同的 只需要比较左侧相同的部分就可以了
        int aLength = 0;
        int bLength = 0;
        //计算A和B的长度
        ListNode p = headA;
        while (p.next != null){
            aLength++;
            p = p.next;
        }
        p = headB;
        while (p.next != null){
            bLength++;
            p = p.next;
        }
        //截断比较长的队列 因为长出来的那一部分肯定不能是相交的点
        while (aLength > bLength){
            aLength--;
            headA = headA.next;
        }
        while (bLength > aLength){
            bLength--;
            headB = headB.next;
        }
        while (headA != null){
            if (headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public static void main(String[] args){

    }
}