public class RCToxicMaterials extends RCHeavyFreight{
    ElectricalGrid electricalGrid;
    double curTotalVolume;
    double curTotalWeight;
    String name;
    int ID;   
    public RCToxicMaterials(double maxTotalWeight, double maxTotalVolume,String securityInfo){
        super(maxTotalWeight,maxTotalVolume,securityInfo);
        this.ID = ID;
        this.curTotalVolume = 0;
        this.curTotalWeight = 0;
        this.electricalGrid = new ElectricalGrid(false);
        this.name = "Toxic Materials";
    }
    @Override
    public void addAtOnce(double TotalWeight, double TotalVolume) {
        if(curTotalWeight > 0 || curTotalVolume > 0){
            try {
                throw new RuntimeException("You cannot add more Toxic Materials");
            }catch (RuntimeException e){
                System.err.println("You cannot add more Toxic Materials");
            }
        }
        else if(TotalWeight > maxTotalWeight){
            try {
                throw new RuntimeException("Too much weight of Toxic Materials");
            }catch (RuntimeException e){
                System.err.println("Too much weight of Toxic Materials");
            }
        }else if(TotalVolume > maxTotalVolume){
            try {
                throw new RuntimeException("Not enough space for Toxic Materials");
            }catch (RuntimeException e){
                System.err.println("Not enough space for Toxic Materials");
            }
        }else{
            this.curTotalWeight = TotalWeight;
            this.curTotalVolume = TotalVolume;
            System.out.println("Added successfully");
        }
    }

    @Override
    public void removeAtOnce() {
        this.curTotalWeight = 0;
        this.curTotalVolume = 0;
        System.out.println("Removed successfully");
    }
}
