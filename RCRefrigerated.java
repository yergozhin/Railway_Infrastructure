public class RCRefrigerated extends RCBasicFreight{
    double degreeReq;
    ElectricalGrid electricalGrid;
    double curTotalVolume;
    double curTotalWeight;
    int ID; 
    boolean cling = false;
    boolean hting = false;
    String name;
    public RCRefrigerated(double degreeReq, double maxTotalWeight, double maxTotalVolume){
        super(maxTotalWeight,maxTotalVolume);
        this.ID = ID;
        this.degreeReq = degreeReq;
        this.curTotalVolume = 0;
        this.curTotalWeight = 0;
        this.electricalGrid = new ElectricalGrid(true);
        this.name = "Refrigerated";
    }

    @Override
    public void addSome(double TotalWeight, double TotalVolume) {
        if(TotalWeight + curTotalWeight > maxTotalWeight){
            try {
                throw new RuntimeException("Too heavy object");
            }catch (RuntimeException e){
                System.err.println("Too heavy object");
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
    public void cooling(){
        if(cling == true){
            System.out.println("It is already cooling");
            return;
        }
        if(degreeReq > 0){
            System.out.println("Warning, this refrigerator more needs heating");
            return;
        }
        cling = true;
        System.out.println("Cooling started");
    }
    public void heating(){
        if(hting == true){
            System.out.println("It is already heating");
            return;
        }
        if(degreeReq <= 0){
            System.out.println("Warning, this refrigerator more needs cooling");
            return;
        }
        hting = true;
        System.out.println("Heating started");
    }
}
