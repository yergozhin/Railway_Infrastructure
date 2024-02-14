import java.util.ArrayList;
import java.util.List;
 
public class Locomotive {
    public int maxRC;
    public double maxWeight;
    public double maxEG;
    Node head;
    int RC;
    int EG;
    int Weight;
    Station station0;
    Station station1;
    String name;
    double speed;
    static int index = 0;
    int ID;
    List<Station> list;
    public Locomotive(String name,Station station0, Station station1,double speed){
        this.name = name;
        this.station0 = station0;
        this.station1 = station1;
        this.speed = speed;
        this.maxEG = (int)(Math.random()*3) + 3;
        this.maxRC = (int)(Math.random()*5) + 5;
        this.maxWeight = (double)(Math.random()*1000) + 1000;
        this.RC = 0;
        this.EG = 0;
        this.Weight = 0;
        this.ID = index;
        this.list = new ArrayList<>();
        index++;
    }
    public void addFront(RailroadCar railroadCar) {
        head = new Node(railroadCar,head);
    }
    public void addBack(RailroadCar railroadCar) {
        if(RC + 1 > maxRC){
            try {
                throw new Exception("Too many railroad cars");
            }catch (Exception e){
                System.err.println("Caught exception: ");
                e.printStackTrace();
            }
        }else if(Weight + railroadCar.weight > maxWeight){
            try {
                throw new Exception("The weight of the trainset becomes too heavy");
            }catch (Exception e){
                System.err.println("Caught exception: ");
                e.printStackTrace();
            }
        }else if(railroadCar.electricalGrid.connection && EG + 1 > maxEG){
            try {
                throw new Exception("Not enough electricity grid to connect to");
            }catch (Exception e){
                System.err.println("Caught exception: ");
                e.printStackTrace();
            }
        }
        this.RC = RC + 1;
        this.Weight = Weight + railroadCar.weight;
        if(railroadCar.electricalGrid.connection){
            this.EG = EG + 1;
        }
        if (head == null) {
            addFront(railroadCar);
            return;
        }
        Node tmp = head;
        while(tmp.next != null)
            tmp = tmp.next;
        tmp.next = new Node(railroadCar);
    }
    public void showList() {
        System.out.print("[ ");
        Node tmp = head;
        while(tmp != null) {
            System.out.print(tmp.railroadCar.ID + " ");
            tmp = tmp.next;
        }
        System.out.println("]");
    }
    public void removeAll(){
        this.EG = 0;
        this.RC = 0;
        this.Weight = 0;
        head = null;
    }
    public void removeSome(int i){
        if(head == null){
            System.out.println("No such a railroad car");
            return;
        }
        if(head.railroadCar.ID == i){
            this.RC = RC - 1;
            this.Weight = Weight - head.railroadCar.weight;
            if(head.railroadCar.electricalGrid.connection){
                this.EG = EG - 1;
            }
            head = head.next;
            System.out.println("Removed Successfully");
            return;
        }
        Node temp = head;
        while(temp != null){
            if(temp.next != null && temp.next.railroadCar.ID == i){
                break;
            }
            temp = temp.next;
        }
        if(temp == null || temp.next == null){
            System.out.println("No such a railroad car");
            return;
        }
        this.RC = RC - 1;
        this.Weight = Weight - temp.next.railroadCar.weight;
        if(temp.next.railroadCar.electricalGrid.connection){
            this.EG = EG - 1;
        }
        Node Next = temp.next.next;
        temp.next = Next;
        System.out.println("Removed Successfully");
    }
}
