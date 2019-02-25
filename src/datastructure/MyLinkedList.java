package datastructure;

import java.util.Stack;

/**
 * 设计一个单链表
 */
public class MyLinkedList {
    int size = 0;
    Node head = null;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0|| index >= size || size <= 0){
            return -1;
        }
        Node cur = head;
        //逐渐循环到index并返回value
        for (int i=0;i<index;i++){
            cur = cur.next;
        }
        return cur.value;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node headNode = new Node(val,head);
        head = headNode;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node tailNode = new Node(val,null);
        Node cur = head;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = tailNode;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size){
            return;
        }
        if (index == 0){
            addAtHead(val);
            return;
        }
        Node cur = head;
        for (int i =0;i<index-1;i++){
            cur = cur.next;
        }
        //指向原来的next
        Node iNode = new Node(val,cur.next);
        //原来的next指向该节点
        cur.next = iNode;
        size++;

    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size){
            return;
        }
        if (index == 0){
            head = head.next;
            size--;
            return;
        }
        Node cur = head;
        for (int i=0;i<index-1;i++){
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;
    }

    //删除链表的倒数N个元素
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //删除的是倒数第N个元素 也就是应该删除length - N的元素
        //可以用两个指针 先让一个指针多走N个 然后让第二个指针开始指向头结点
        //接着两个一起走 第一走到length结尾的时候 第二个相当于走到了length-N
        ListNode first = head;
        ListNode main = head;
        while (n > 0){
            n--;
            first = first.next;
        }
        //如果删除的是首节点 那first会遍历完这个列表 first会变为null 此时直接去掉head即可
        if (first == null){
            head = head.next;
            return head;
        }
        //继续开始循环
        while (first.next != null){
            first = first.next;
            main = main.next;
        }
        main.next = main.next.next;
        return head;
    }

    //反转一个链表
    public ListNode reverseList(ListNode head){
        //第一种方式 非递归的
        /*if (head == null || head.next == null){
            return head;
        }
        //使用三个临时指针 分别存储其上一个 主节点 和下一个
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = head.next.next;
        pre.next = null;//头节点的指向为null
        while (next != null){
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;//所有的指针移动一位
        }
        //如果为最后一个节点的next为null的时候，其作为头结点 将其指向上一个节点
        cur.next = pre;
        return cur;*/
        //第二种方式 递归的
        if (head == null || head.next == null){
            return head;
        }else {
            ListNode node = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return node;
        }
    }

    //移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        while (head != null && head.val == val){
            head = head.next;
        }
        ListNode p = head;
        while (p != null && p.next != null){
            if (p.next.val == val){
                p.next = p.next.next;
            }else {
                p = p.next;
            }
        }
        return head;
    }

    //奇偶链表问题
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode even = head;
        ListNode odd = head.next;
        while (odd != null && odd.next != null){
            ListNode tmp = even.next;
            even.next = odd.next;
            odd.next = odd.next.next;
            even.next.next = tmp;
            even = even.next;
            odd = odd.next;
        }
        return head;
    }

    //回文链表问题 主要思路还是要先找中点  然后将左侧入栈之后 开始逐个比较
    public boolean isPalindrome(ListNode head) {
        //快慢指针找中点
        ListNode slow = head;
        ListNode fast = head;
        Stack<Integer> stack = new Stack<>();
        if (head == null || head.next == null){
            return true;
        }
        while (fast != null && fast.next != null){
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        //如果是奇数个的话 将该值也入栈
        if (fast!=null){
            stack.push(slow.val);
        }
        //继续从中点向后
        while (slow != null){
            int v = stack.pop();
            if (v != slow.val){
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args){
        /*MyLinkedList obj = new MyLinkedList();
        int val0 = obj.get(0);
        //obj.addAtHead(1);
        obj.addAtIndex(1,2);
        int val2 = obj.get(0);
        int val1 = obj.get(1);
        obj.addAtIndex(0,1);
        int val3 = obj.get(0);
        int val5 = obj.get(1);*/
        ListNode tail = new ListNode(1,null);
        ListNode tail1 = new ListNode(0,tail);
        ListNode head = new ListNode(1,tail1);
        //ListNode listNode = new MyLinkedList().removeNthFromEnd(head, 1);
        //ListNode listNode = new MyLinkedList().removeElements(head,1);
        boolean x = new MyLinkedList().isPalindrome(head);
        System.out.println(x);
        //int val3 = obj.get(2);
        /*int index = 1;
        int param_1 = obj.get(index);
        obj.addAtHead(100);
        obj.addAtTail(200);
        obj.addAtIndex(index,200);
        obj.deleteAtIndex(index);*/
    }
}

class Node{
    int value;
    Node next;
    public Node(int value,Node next){
        this.value = value;
        this.next = next;
    }
}
