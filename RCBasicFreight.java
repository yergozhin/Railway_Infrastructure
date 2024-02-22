public abstract class RCBasicFreight extends RailroadCar{
    double maxTotalWeight;
    double maxTotalVolume;
    ElectricalGrid electricalGrid;
    String name; 
    int ID;
    public RCBasicFreight(double maxTotalWeight, double maxTotalVolume){
        super();
        this.ID = ID;
        this.maxTotalWeight = maxTotalWeight;
        this.maxTotalVolume = maxTotalVolume;
        this.electricalGrid = new ElectricalGrid(false);
        this.name = "Basic Freight";
    }
    public abstract void addSome(double TotalWeight, double TotalVolume);
    public abstract void removeSome(double TotalWeight, double TotalVolume);
    public abstract void removeAll();

}
