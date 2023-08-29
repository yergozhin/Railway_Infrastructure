public class RCBaggage extends RailroadCar {
    ElectricalGrid electricalGrid;
    double maxTotalWeight;
    double maxTotalVolume;
    double curTotalWeight;
    double curTotalVolume;
    String name = "Baggage";
    int ID;
    public RCBaggage(double maxTotalWeight, double maxTotalVolume){
        super();
        this.ID = ID;
        this.maxTotalWeight = maxTotalWeight;
        this.maxTotalVolume = maxTotalVolume;
        this.curTotalWeight = 0;
        this.curTotalVolume = 0;
        this.electricalGrid = new ElectricalGrid(false);
        this.name = "Baggage";
    }

}
