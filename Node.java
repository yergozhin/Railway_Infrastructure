public class Node {
    RailroadCar railroadCar;
    Node next;
    Node(RailroadCar railroadCar, Node next) {
        this.railroadCar = railroadCar;
        this.next = next;
    } 
    Node(RailroadCar railroadCar) {
        this(railroadCar,null);
    }
}
