public class RailroadCar {
    ElectricalGrid electricalGrid;
    static int count = 0;
    int weight;
    //String name = "Common";
    int ID;
    public RailroadCar(){
        count++; 
        this.ID = count;
        weight = 0;
        this.electricalGrid = new ElectricalGrid(false);
        //this.name = "Common";
    }
}
