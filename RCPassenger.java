import java.util.HashMap;
import java.util.Map;

public class RCPassenger extends RailroadCar{
    Map<Person,Equipment> map = new HashMap<>();
    int numOfSeats; 
    double maxEqWeight;
    int numOfLamps;
    ElectricalGrid electricalGrid;
    int numOfPeople;
    double EqWeight;
    int ID;
    String name;
    boolean reclinedSeats = false;
    public RCPassenger(int numOfSeats,double maxEqWeight,int numOfLamps){
        super();
        this.numOfSeats = numOfSeats;
        this.maxEqWeight = maxEqWeight;
        this.numOfLamps = numOfLamps;
        this.ID = ID;
        this.numOfPeople = 0;
        this.EqWeight = 0;
        this.electricalGrid = new ElectricalGrid(true);
        this.name = "Passenger";
    }
    public void addPeople(Person person, Equipment equipment){
        if(1 + numOfPeople > numOfSeats){
            try{
                throw new RuntimeException("There is not left so many seats for people");
            }
            catch (RuntimeException e){
                System.err.println(e.getMessage());
                //System.err.println(e.getMessage());
            }
        }else if(equipment.weight + EqWeight >maxEqWeight){
            try {
                throw new RuntimeException("Equipments are too heavy");
            }catch (RuntimeException e){
                System.err.println("Equipments are too heavy");
            }
        }else{
            map.put(person,equipment);
            this.numOfPeople = numOfPeople + 1;
            this.EqWeight = EqWeight + equipment.weight;
            System.out.println("People were successfully loaded inside the passenger railroad car");
        }
    }
    public void removeSomePeople(int id){
        for(Map.Entry<Person,Equipment> entry : map.entrySet()){
            if(entry.getKey().ID == id){
                this.numOfPeople = numOfPeople - 1;
                this.EqWeight = EqWeight - entry.getValue().weight;
                map.remove(entry.getKey(),entry.getValue());
                return;
            }
        }
        System.out.println("This Person doesn't exist");
    }
    public void removeAllPeople(){
        map = new HashMap<>();
        System.out.println("People were removed successfully");
    }
    public void reclineSeats(){
        if(reclinedSeats){
            System.out.println("Seats were already reclined");
            return;
        }
        System.out.println("Seats reclined");
    }
    public void returningBackToNormalCondition(){
        if(!reclinedSeats){
            System.out.println("Seats were already in their normal condition");
            return;
        }
        System.out.println("Seats turned into normal condition");
    }

}
