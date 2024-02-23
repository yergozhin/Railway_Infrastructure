public class RCLiquid extends RCBasicFreight{
    ElectricalGrid electricalGrid;
    double curTotalVolume;
    double curTotalWeight;
    int ID; 
    String name = "Liquid";
    public RCLiquid(double maxTotalWeight, double maxTotalVolume){
        super(maxTotalWeight,maxTotalVolume);
        this.ID = ID;
        this.curTotalVolume = 0;
        this.curTotalWeight = 0;
        this.electricalGrid = new ElectricalGrid(false);
        this.name = "Liquid";
    }

    @Override
    public void addSome(double TotalWeight, double TotalVolume) {
        if(TotalWeight + curTotalWeight > maxTotalWeight){
            try {
                throw new RuntimeException("Too heavy Liquid");
            }catch (RuntimeException e){
                System.err.println("Too heavy Liquid");
            }
        }else if(TotalVolume + curTotalVolume > maxTotalVolume){
            try {
                throw new RuntimeException("Not enough space");
            }catch (RuntimeException e){
                System.err.println("Not enough space");
            }
        }else{
            this.curTotalWeight = curTotalWeight + TotalWeight;
            this.curTotalVolume = curTotalVolume + TotalVolume;
            System.out.println("Successfully added");
        }
    }

    @Override
    public void removeSome(double TotalWeight, double TotalVolume) {
        if(curTotalWeight - TotalWeight <= 0){
            this.curTotalWeight = 0;
            if(curTotalVolume - TotalVolume <= 0){
                this.curTotalVolume = 0;
                System.out.println("Successfully removed");
            }else{
                this.curTotalVolume = 0;
                System.out.println("There is some mystery");
            }
        }else if(curTotalVolume - TotalVolume <= 0){
            this.curTotalVolume = 0;
            this.curTotalWeight = 0;
            System.out.println("There is some mystery");
        }else{
            this.curTotalWeight = curTotalWeight - TotalWeight;
            this.curTotalVolume = curTotalVolume - TotalVolume;
            System.out.println("Successfully removed");
        }
    }

    @Override
    public void removeAll() {
        this.curTotalVolume = 0;
        this.curTotalWeight = 0;
        System.out.println("Successfully removed");
    }
}
