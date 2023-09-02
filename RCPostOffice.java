public class RCPostOffice extends RailroadCar{
    int numOfMailboxes;
    double maxTotalWeightOfMails;
    ElectricalGrid electricalGrid;
    String name = "Post Office";
    int ID;
    public RCPostOffice(int numOfMailboxes, double maxTotalWeightOfMails){
        super();
        this.numOfMailboxes = numOfMailboxes;
        this.maxTotalWeightOfMails = maxTotalWeightOfMails;
        this.ID = ID;
        this.electricalGrid = new ElectricalGrid(true);
        this.name = "Post Office";
    }
}
