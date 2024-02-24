import java.util.Scanner;

public class RCRestaurant extends RailroadCar{
    int numOfTables;
    int numOfRefrigerators; 
    double maxEqWeight;
    ElectricalGrid electricalGrid;
    String name;
    int ID;
    public RCRestaurant(int numOfTables, int numOfRefrigerators, double maxEqWeight){
        super();
        this.ID = ID;
        this.numOfTables = numOfTables;
        this.numOfRefrigerators = numOfRefrigerators;
        this.maxEqWeight = maxEqWeight;
        this.electricalGrid = new ElectricalGrid(true);
        this.name = "Restaurant";
    }
    public void seeAndBuyFromMenu(){
        double totalAmount = 0;
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        while(quit != true) {
            System.out.println("Select food and drinks:");
            System.out.println("Food(1-3): ");
            System.out.println("(1) Pasta 14$");
            System.out.println("(2) Burger 10$");
            System.out.println("(3) Pizza 19$");
            System.out.println("(4) Nothing");
            double food = in.nextDouble();
            System.out.println("Drinks(1-3): ");
            System.out.println("(1) Pepsi 5$");
            System.out.println("(2) Tea 3$");
            System.out.println("(3) Water 1.5$");
            System.out.println("(4) Nothing");
            double drinks = in.nextDouble();
            System.out.println("Select 'q' to quit or 'c' to continue");
            String c = in.next();
            if(c.equals("q")){
                quit = true;
            }
            if(food == 1){
                totalAmount += 14;
            }else if(food == 2){
                totalAmount += 10;
            }else if(food == 3){
                totalAmount += 19;
            }
            if(drinks == 1){
                totalAmount += 5;
            }else if(drinks == 2){
                totalAmount += 3;
            }else if(drinks == 3){
                totalAmount += 1.5;
            }
        }
        System.out.println("Your total amount is " + totalAmount);
    }
}
