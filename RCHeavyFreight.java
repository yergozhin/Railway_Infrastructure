public abstract class RCHeavyFreight extends RailroadCar{
    double maxTotalWeight;
    double maxTotalVolume;
    ElectricalGrid electricalGrid;
    String name = "Heavy Freight";
    int ID;
    String securityInfo;
    public RCHeavyFreight(double maxTotalWeight, double maxTotalVolume,String securityInfo){
        super();
        this.ID = ID;
        this.maxTotalWeight = maxTotalWeight;
        this.maxTotalVolume = maxTotalVolume;
        this.securityInfo = securityInfo;
        this.electricalGrid = new ElectricalGrid(false);
        this.name = "Heavy Freight";
    }
    public abstract void addAtOnce(double TotalWeight, double TotalVolume);
    public abstract void removeAtOnce();
}
