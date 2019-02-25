package datastructure;

/**
 * 双链表设计
 */
public class DoubleList {
    /** Initialize your data structure here. */
    int size = 0;
    DNode head;
    public DoubleList() {
        size = 0;
        head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index<0||index>=size){
            return -1;
        }
        DNode node = head;
        if (node == null){
            return -1;
        }
        for (int i=0;i<index;i++){
            node = node.next;
        }
        return node.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        DNode headN = head;
        DNode node = new DNode(val,null,headN);
        if (headN == null){
            head = node;
            size++;
            return;
        }
        headN.pre = node;
        head = node;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        DNode tail = head;
        for (int i=0;i<size-1;i++){
            tail = tail.next;
        }
        DNode node = new DNode(val,tail,null);
        tail.next = node;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0||index > size){
            return;
        }
        if (index == 0){
            addAtHead(val);
            return;
        }
        DNode nextN = head;
        for (int i=0;i<index-1;i++){
            nextN = nextN.next;
        }
        DNode preN = nextN.pre;
        DNode node = new DNode(val,preN,nextN);
        preN.next = node;
        nextN.pre = node;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index<0||index>=size){
            return;
        }
        DNode nextN = head;
        for (int i=0;i<index;i++){
            nextN = nextN.next;
        }
        DNode preN = nextN.pre;
        DNode nN = nextN.next;
        preN.next = nN;
        if (nN != null){
            nN.pre = preN;
        }
        size--;
    }

    public static void main(String[] args){
        DoubleList obj = new DoubleList();
        obj.addAtHead(1);
        obj.addAtIndex(1,2);
        int param_1 = obj.get(1);
        int param_2 = obj.get(0);
        int param_3 = obj.get(2);

        /*obj.addAtHead(5);
        obj.addAtHead(2);
        obj.deleteAtIndex(1);
        obj.addAtIndex(1,9);
        obj.addAtHead(4);
        obj.addAtHead(9);
        obj.addAtHead(8);
        int param_1 = obj.get(3);
        obj.addAtTail(1);
        obj.addAtIndex(3,6);
        obj.addAtHead(3);*/

        //obj.deleteAtIndex(1);
        //int param_2 = obj.get(1);
    }
}

class DNode{
    int val;
    DNode pre;
    DNode next;
    public DNode(int val,DNode pre,DNode next){
        this.val = val;
        this.next = next;
        this.pre = pre;
    }
}
