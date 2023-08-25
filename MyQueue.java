public class MyQueue {
    private static class Node {
        Pair pair;
        Node next = null;
        Node(Pair d) { pair = d; }
    }
    private Node head, tail;
    public MyQueue() {
        head = tail = null;
    }
    public void enqueue(Pair s) {
        if (head == null)
            head = tail = new Node(s);
        else
            tail = tail.next = new Node(s);
    }
    public Pair dequeue() {
        Pair s = head.pair;
        if ((head = head.next) == null) tail = null;
        return s;
    }
    public boolean empty() {
        return head == null;
    }
}
