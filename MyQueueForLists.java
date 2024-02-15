public class MyQueueForLists {
    private static class Node {
        PairForListAndLoco pair;
        MyQueueForLists.Node next = null;
        Node(PairForListAndLoco d) { pair = d; }
    }
    private MyQueueForLists.Node head, tail;
    public MyQueueForLists() {
        head = tail = null; 
    }
    public void enqueue(PairForListAndLoco s) {
        if (head == null)
            head = tail = new MyQueueForLists.Node(s);
        else
            tail = tail.next = new MyQueueForLists.Node(s);
    }
    public PairForListAndLoco dequeue() {
        PairForListAndLoco s = head.pair;
        if ((head = head.next) == null) tail = null;
        return s;
    }
    public boolean empty() {
        return head == null;
    }
}
