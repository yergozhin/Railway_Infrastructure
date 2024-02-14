import javax.print.attribute.standard.Destination;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List; 
import java.util.Scanner;

public class Menu extends Thread{
    List<Station> stations;
    List<Trainset> trainsets;
    List<Connections.Triplet> connections;
    MyQueueForLists myQueueForLists;
    Connections connection;
    public Menu(List<Station> stations, List<Trainset> trainsets, List<Connections.Triplet> connections,MyQueueForLists myQueueForLists,Connections connection){
        this.trainsets = trainsets;
        this.connections = connections;
        this.connection = connection;
        this.myQueueForLists = myQueueForLists;
        this.stations = stations;
    }
    public void menu() throws InterruptedException, IOException {
        System.out.println("Select an option(1-14): ");
        System.out.println("(1) Create locomotive");
        System.out.println("(2) Create railroad car and add it to a locomotive");
        System.out.println("(3) Create railway station");
        System.out.println("(4) Create connection between stations");
        System.out.println("(5) Go back");
        System.out.println("(6) Remove locomotive");
        System.out.println("(7) Remove railroad car from locomotive");
        System.out.println("(8) Remove station");
        System.out.println("(9) Remove connection between stations");
        System.out.println("(10) Remove all railroad cars from locomotive");
        System.out.println("(11) Run");
        System.out.println("(12) Create path and make it already run");
        System.out.println("(13) Report");
        System.out.println("(14) Additional functionalities of railroad cars");
        System.out.println("(15) Start to save everything into a file");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        if(x == 1){
            System.out.print("Select home station's index(");
            for(int i = 0; i < stations.size(); i = i + 1){
                System.out.print(stations.get(i).ID + " ");
            }
            System.out.print("):");
            System.out.println();
            x = in.nextInt();
            Station station0 = null;
            boolean numIsCorrect = false;
            while(!numIsCorrect) {
                for (int i = 0; i < stations.size(); i = i + 1) {
                    if (stations.get(i).ID == x) {
                        station0 = stations.get(i);
                        numIsCorrect = true;
                        break;
                    }
                }
                if(!numIsCorrect) {
                    System.out.println("Try again");
                    menu();
                    return;
                }
            }
            System.out.print("Select destination station's index(");
            for(int i = 0; i < stations.size(); i = i + 1){
                System.out.print(stations.get(i).ID + " ");
            }
            System.out.print("):");
            System.out.println();
            x = in.nextInt();
            Station station1 = null;
            numIsCorrect = false;
            while(!numIsCorrect) {
                for (int i = 0; i < stations.size(); i = i + 1) {
                    if (stations.get(i).ID == x) {
                        station1 = stations.get(i);
                        numIsCorrect = true;
                    }
                }
                if(!numIsCorrect) {
                    System.out.println("Try again");
                    menu();
                    return;
                }
            }
            System.out.println("Assign a speed to locomotive: ");
            double speed = in.nextDouble();
            int j = -1;
            for(int i = 0; i < trainsets.size(); i = i + 1){
                j = Math.max(j,trainsets.get(i).locomotive.ID);
            }
            Locomotive locomotive = new Locomotive("L" + (j + 1),station0,station1,speed);
            locomotive.ID = j + 1;
            Trainset trainset = new Trainset(locomotive);
            trainsets.add(trainset);
            System.out.println("Locomotive " + locomotive.name + " created");
            menu();
        }
        else if(x == 2){
            System.out.println("Select type of the railroad car(0-7):");
            System.out.println("(0) Baggage");
            System.out.println("(1) Explosives");
            System.out.println("(2) Gaseous");
            System.out.println("(3) Liquid");
            System.out.println("(4) Liquid Toxic Materials");
            System.out.println("(5) Refrigerated");
            System.out.println("(6) Restaurant");
            System.out.println("(7) Toxic Materials");
            System.out.println("(8) Passenger");
            System.out.println("(9) Post Office");
            x = in.nextInt();
            RailroadCar railroadCar = null;
            if(x == 0){
                System.out.println("Assign maximum total weight of the railroad car and maximum total volume:");
                double weight = in.nextDouble();
                double volume = in.nextDouble();
                railroadCar = new RCBaggage(weight,volume);
            }else if(x == 1){
                System.out.println("Assign maximum total weight of the railroad car and maximum total volume:");
                double weight = in.nextDouble();
                double volume = in.nextDouble();
                System.out.println("Type Security information: ");
                String si = in.next();
                railroadCar = new RCExplosives(weight,volume,si);
            }else if(x == 2){
                System.out.println("Assign maximum total weight of the railroad car and maximum total volume:");
                double weight = in.nextDouble();
                double volume = in.nextDouble();
                railroadCar = new RCGaseous(weight,volume);
            }else if(x == 3){
                System.out.println("Assign maximum total weight of the railroad car and maximum total volume:");
                double weight = in.nextDouble();
                double volume = in.nextDouble();
                railroadCar = new RCLiquid(weight,volume);
            }else if(x == 4){
                System.out.println("Assign maximum total weight of the railroad car and maximum total volume:");
                double weight = in.nextDouble();
                double volume = in.nextDouble();
                System.out.println("Type Security information: ");
                String si = in.next();
                railroadCar = new RCLiquidToxicMaterials(weight,volume,si);
            }else if(x == 5){
                System.out.println("Assign maximum total weight of the railroad car and maximum total volume:");
                double weight = in.nextDouble();
                double volume = in.nextDouble();
                System.out.println("Assign temperature inside the refrigerator: ");
                double degree = in.nextDouble();
                railroadCar = new RCRefrigerated(degree,weight,volume);
            }else if(x == 6){
                System.out.println("Assign maximum equipment weight: ");
                double equipment = in.nextDouble();
                System.out.println("Assign number of tables and number of refrigerators: ");
                int tables = in.nextInt();
                int refrigerators = in.nextInt();
                railroadCar = new RCRestaurant(tables,refrigerators,equipment);
            }else if(x == 7){
                System.out.println("Assign maximum total weight of the railroad car and maximum total volume:");
                double weight = in.nextDouble();
                double volume = in.nextDouble();
                System.out.println("Type Security information: ");
                String si = in.next();
                railroadCar = new RCToxicMaterials(weight,volume,si);
            }else if(x == 8){
                System.out.println("Assign number of seats and number of lamps: ");
                int seats = in.nextInt();
                int lamps = in.nextInt();
                System.out.println("Assign maximum weight of the equipment: ");
                double equipment = in.nextDouble();
                railroadCar = new RCPassenger(seats,equipment,lamps);
            }else if(x == 9){
                System.out.println("Assign number of mailboxes: ");
                int mailboxes = in.nextInt();
                System.out.println("Assign maximum weight of total mailboxes: ");
                double weight = in.nextDouble();
                railroadCar = new RCPostOffice(mailboxes,weight);
            }
            else{
                System.out.println("Try again");
                menu();
                return;
            }
            System.out.print("Select locomotive from index(");
            for(int i = 0; i < trainsets.size(); i = i + 1){
                System.out.print(trainsets.get(i).locomotive.ID + " ");
            }
            System.out.print("):");
            System.out.println();
            x = in.nextInt();
            boolean check = false;
            for(int i = 0; i < trainsets.size(); i = i + 1){
                if(trainsets.get(i).locomotive.ID == x){
                    trainsets.get(i).locomotive.addBack(railroadCar);
                    check = true;
                }
            }
            if(check == false){
                System.out.println("There is no such a locomotive");
            }else{
                System.out.println("Railroad successfully added to locomotive");
            }
            menu();
        }
        else if(x == 3){
            int j = -1;
            for(int i = 0; i < stations.size(); i = i + 1){
                j = Math.max(j,stations.get(i).ID);
            }
            stations.add(new Station("S" + (j + 1)));
            stations.get(stations.size() - 1).ID = j + 1;
            System.out.println("Station " + stations.get(stations.size() - 1).name + " created");
            menu();
        }
        else if(x == 4){
            System.out.print("Select first station from index(");
            for(int i = 0; i < stations.size(); i = i + 1){
                System.out.print(stations.get(i).ID + " ");
            }
            System.out.print("):");
            System.out.println();
            x = in.nextInt();
            Station station0 = null;
            boolean numIsCorrect = false;
            while(!numIsCorrect) {
                for (int i = 0; i < stations.size(); i = i + 1) {
                    if (stations.get(i).ID == x) {
                        station0 = stations.get(i);
                        numIsCorrect = true;
                        break;
                    }
                }
                if(!numIsCorrect) {
                    System.out.println("Try again");
                    menu();
                    return;
                }
            }
            System.out.print("Select second station from index(");
            for(int i = 0; i < stations.size(); i = i + 1){
                System.out.print(stations.get(i).ID + " ");
            }
            System.out.print("):");
            System.out.println();
            x = in.nextInt();
            Station station1 = null;
            numIsCorrect = false;
            while(!numIsCorrect) {
                for (int i = 0; i < stations.size(); i = i + 1) {
                    if (stations.get(i).ID == x) {
                        station1 = stations.get(i);
                        numIsCorrect = true;
                    }
                }
                if(!numIsCorrect) {
                    System.out.println("Try again");
                    menu();
                    return;
                }
            }
            System.out.println("Assign a distance between first stations and second stations: ");
            double distance = in.nextDouble();
            connection.list.add(new Connections.Triplet(station0,station1,distance));
            System.out.println("Connection between station " + station0.name + " and station " + station1.name + " created");
            menu();
        }
        else if(x == 5){
            return;
        }else if(x == 6){
            System.out.println("Select index from(");
            for(int i = 0; i < trainsets.size(); i = i + 1){
                System.out.print(trainsets.get(i).locomotive.ID + " ");
            }
            System.out.print(")");
            System.out.println();
            x = in.nextInt();
            boolean check = false;
            for(int i = 0; i < trainsets.size(); i = i + 1){
                if(trainsets.get(i).locomotive.ID == x){
                    MyQueueForLists myQueueForLists1 = new MyQueueForLists();
                    while (!myQueueForLists.empty()){
                        PairForListAndLoco pair = myQueueForLists.dequeue();
                        if(pair.trainset.locomotive.ID != x){
                            myQueueForLists1.enqueue(pair);
                        }
                    }
                    while (!myQueueForLists1.empty()){
                        PairForListAndLoco pair = myQueueForLists1.dequeue();
                        myQueueForLists.enqueue(pair);
                    }
                    trainsets.remove(i);
                    check = true;
                    i = i - 1;
                }
            }
            if(check == true){
                System.out.println("Locomotive removed successfully");
                menu();
            }else {
                System.out.println("There is no such a locomotive");
                menu();
            }
        }
        else if(x == 7){
            System.out.println("Select locomotive index from(");
            for(int i = 0; i < trainsets.size(); i = i + 1){
                System.out.print(trainsets.get(i).locomotive.ID + " ");
            }
            System.out.print(")");
            System.out.println();
            x = in.nextInt();
            for(int i = 0; i < trainsets.size(); i = i + 1){
                if(trainsets.get(i).locomotive.ID == x){
                    System.out.print("Select railroad car index from");
                    trainsets.get(i).locomotive.showList();
                    System.out.print("");
                    System.out.println();
                    int ind = in.nextInt();
                    trainsets.get(i).locomotive.removeSome(ind);
                    menu();
                }
            }
            System.out.println("There is no such a locomotive");
            menu();
        }else if(x == 8){
            System.out.print("Select station's index(");
            for(int i = 0; i < stations.size(); i = i + 1){
                System.out.print(stations.get(i).ID + " ");
            }
            System.out.print("):");
            System.out.println();
            int ind = in.nextInt();
            for(int i = 0; i < stations.size(); i = i + 1){
                if(ind == stations.get(i).ID){
                    MyQueueForLists myQueueForLists1 = new MyQueueForLists();
                    while (!myQueueForLists.empty()){
                        PairForListAndLoco pair = myQueueForLists.dequeue();
                        boolean check = false;
                        for(int k = 0; k < pair.list.size(); k = k + 1) {
                            if (pair.list.get(k).ID == ind) {
                                check = true;
                            }
                        }
                        if(check == false){
                            myQueueForLists1.enqueue(pair);
                        }
                    }
                    while (!myQueueForLists1.empty()){
                        PairForListAndLoco pair = myQueueForLists1.dequeue();
                        myQueueForLists.enqueue(pair);
                    }
                    stations.remove(i);
                    System.out.println("Station removed successfully");
                    menu();
                    return;
                }
            }
            System.out.println("There is no such a station");
            menu();
        }else if(x == 9){
            System.out.print("Select first station from index(");
            for(int i = 0; i < stations.size(); i = i + 1){
                System.out.print(stations.get(i).ID + " ");
            }
            System.out.print("):");
            System.out.println();
            x = in.nextInt();
            Station station0 = null;
            boolean numIsCorrect = false;
            while(!numIsCorrect) {
                for (int i = 0; i < stations.size(); i = i + 1) {
                    if (stations.get(i).ID == x) {
                        station0 = stations.get(i);
                        numIsCorrect = true;
                        break;
                    }
                }
                if(!numIsCorrect) {
                    System.out.println("Try again");
                    menu();
                    return;
                }
            }
            System.out.print("Select second station from index(");
            for(int i = 0; i < stations.size(); i = i + 1){
                System.out.print(stations.get(i).ID + " ");
            }
            System.out.print("):");
            System.out.println();
            x = in.nextInt();
            Station station1 = null;
            numIsCorrect = false;
            while(!numIsCorrect) {
                for (int i = 0; i < stations.size(); i = i + 1) {
                    if (stations.get(i).ID == x) {
                        station1 = stations.get(i);
                        numIsCorrect = true;
                    }
                }
                if(!numIsCorrect) {
                    System.out.println("Try again");
                    menu();
                    return;
                }
            }
            boolean see = false;
            for(int i = 0; i < connection.list.size(); i = i + 1){
                if((connection.list.get(i).station0.ID == station0.ID && connection.list.get(i).station1.ID == station1.ID)||(connection.list.get(i).station1.ID == station0.ID && connection.list.get(i).station0.ID == station1.ID)){
                    MyQueueForLists myQueueForLists1 = new MyQueueForLists();
                    while (!myQueueForLists.empty()){
                        PairForListAndLoco pair = myQueueForLists.dequeue();
                        boolean check = false;
                        for(int k = 1; k < pair.list.size(); k = k + 1){
                            if((pair.list.get(k - 1).ID == station0.ID && pair.list.get(k).ID == station1.ID) || (pair.list.get(k).ID == station0.ID && pair.list.get(k - 1).ID == station1.ID)){
                                check = true;
                            }
                        }
                        if(check == false){
                            myQueueForLists1.enqueue(pair);
                        }
                    }
                    while (!myQueueForLists1.empty()){
                        PairForListAndLoco pair = myQueueForLists1.dequeue();
                        myQueueForLists.enqueue(pair);
                    }
                    connection.list.remove(i);
                    see = true;
                    i = i - 1;
                }
            }
            if(see == true){
                System.out.println("Connection removed successfully");
                menu();
                return;
            }
            System.out.println("There was no such a connection");
            menu();
        }else if(x == 10){
            System.out.println("Select locomotive index from(");
            for(int i = 0; i < trainsets.size(); i = i + 1){
                System.out.print(trainsets.get(i).locomotive.ID + " ");
            }
            System.out.print(")");
            System.out.println();
            x = in.nextInt();
            for(int i = 0; i < trainsets.size(); i = i + 1){
                if(trainsets.get(i).locomotive.ID == x){
                    trainsets.get(i).locomotive.removeAll();
                    System.out.println("Railroads removed successfully");
                    menu();
                }
            }
            System.out.println("There is no such a locomotive");
            menu();
        }else if(x == 11){
            Run run = new Run(myQueueForLists,connection,stations, trainsets,connections);
            run.start();
            menu();
        }else if(x == 12){
            System.out.println("Select trainset's ID: ");
            for(int i = 0; i < trainsets.size(); i = i + 1){
                System.out.print(trainsets.get(i).locomotive.ID + " ");
            }
            x = in.nextInt();
            System.out.println();
            int index = -1;
            for(int i = 0; i < trainsets.size(); i = i + 1){
                if(trainsets.get(i).locomotive.ID == x){
                    index = i;
                }
            }
            if(index == -1){
                System.out.println("This trainset is already running or it does not exist");
                menu();
                return;
            }
            Path path = new Path();
            List<Station> list = path.makePath(connection,trainsets.get(index).locomotive.station0,trainsets.get(index).locomotive.station1,stations);
            if(list.get(0).name == "noPath"){
                System.out.println("There is no such a path for locomotive");
                menu();
                return;
            }
            PairForListAndLoco pair = new PairForListAndLoco(trainsets.get(index),list,1,true,0);
            trainsets.get(index).locomotive.list = list;
            myQueueForLists.enqueue(pair);
            System.out.println("Path successfully created and added to the route");
            menu();
        }else if(x == 13){
            System.out.print("Choose index of a trainset: (");
            for(int i = 0; i < trainsets.size(); i = i + 1){
                System.out.print(trainsets.get(i).locomotive.ID + " ");
            }
            System.out.print(")");
            System.out.println();
            x = in.nextInt();
            for(int i = 0; i < trainsets.size(); i = i + 1){
                if(trainsets.get(i).locomotive.ID == x){
                    System.out.println("Locomotive name: " + trainsets.get(i).locomotive.name + "; Home station: " + trainsets.get(i).locomotive.station0.name + "; Destination station: " + trainsets.get(i).locomotive.station1.name);
                    MyQueueForLists myQueueForLists1 = new MyQueueForLists();
                    boolean forward = true;
                    boolean check = false;
                    double currentDistance = 0;
                    while(!myQueueForLists.empty()){
                        PairForListAndLoco pair = myQueueForLists.dequeue();
                        if(pair.trainset.locomotive.ID == x){
                            currentDistance = pair.distance;
                            forward = pair.forward;
                            check = true;
                        }
                        myQueueForLists1.enqueue(pair);
                    }
                    double totalDistance = 0;
                    while (!myQueueForLists1.empty()){
                        PairForListAndLoco pair = myQueueForLists1.dequeue();
                        if(pair.trainset.locomotive.ID == x){
                            totalDistance = 0;
                            for(int k = 1; k < pair.list.size(); k = k + 1){
                                int st0 = pair.list.get(k - 1).ID;
                                int st1 = pair.list.get(k).ID;
                                for(int j = 0; j < connection.list.size(); j = j + 1){
                                    int cn0 = connection.list.get(j).station0.ID;
                                    int cn1 = connection.list.get(j).station1.ID;
                                    if((st0 == cn0 && st1 == cn1) || (st1 == cn0 && st0 == cn1)){
                                        totalDistance = totalDistance + connection.list.get(j).distance;
                                        break;
                                    }
                                }
                            }
                        }
                        myQueueForLists.enqueue(pair);
                    }
                    //System.out.println(currentDistance);
                    //System.out.println(totalDistance);
                    currentDistance = (currentDistance/totalDistance);
                    currentDistance = currentDistance*100;
                    if(!check){
                        System.out.println("This trainset is not in the route");
                    }else if(forward){
                        System.out.println("Goes from home station until destination station with the " + currentDistance +  "% completed distance");
                    }else if(!forward){
                        System.out.println("Goes from destination station until home station with the " + currentDistance +  "% completed distance");
                    }
                    Node temp = trainsets.get(i).locomotive.head;
                    while(temp != null){
                        if(temp.railroadCar instanceof RCPassenger){
                            System.out.println("Railroad Car Passenger " + temp.railroadCar.ID + ": number of people = " + ((RCPassenger) temp.railroadCar).numOfPeople + "; weight of the transported equipment = " + ((RCPassenger) temp.railroadCar).EqWeight + "; number of lamps = " + ((RCPassenger) temp.railroadCar).numOfLamps + "; number of seats = " + ((RCPassenger) temp.railroadCar).numOfSeats + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                        }else if(temp.railroadCar instanceof RCRestaurant){
                            System.out.println("Railroad Car Restaurant " + temp.railroadCar.ID + ": number of refrigerators = " + ((RCRestaurant) temp.railroadCar).numOfRefrigerators + "; number of tables = " + ((RCRestaurant) temp.railroadCar).numOfTables + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                        }else if(temp.railroadCar instanceof RCRefrigerated){
                            System.out.println("Railroad Car Refrigerator " + temp.railroadCar.ID + ": total weight = " + ((RCRefrigerated) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCRefrigerated) temp.railroadCar).curTotalVolume + "; cooling = " + ((RCRefrigerated) temp.railroadCar).cling + "; heating = " + ((RCRefrigerated) temp.railroadCar).hting + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                        }else if(temp.railroadCar instanceof RCPostOffice){
                            System.out.println("Railroad Car Post Office " + temp.railroadCar.ID + ": number of mailboxes =  " + ((RCPostOffice) temp.railroadCar).numOfMailboxes + "; total weight = " + temp.railroadCar.weight + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                        }else if(temp.railroadCar instanceof RCBaggage){
                            System.out.println("Railroad Car Baggage " + temp.railroadCar.ID + "; total weight = " + ((RCBaggage) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCBaggage) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                        }else if(temp.railroadCar instanceof RCLiquid){
                            System.out.println("Railroad Car Liquid " + temp.railroadCar.ID + "; total weight = " + ((RCLiquid) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCLiquid) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                        }else if(temp.railroadCar instanceof RCExplosives){
                            System.out.println("Railroad Car Explosives " + temp.railroadCar.ID + "; total weight = " + ((RCExplosives) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCExplosives) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection + "; security information = " + ((RCExplosives) temp.railroadCar).securityInfo);
                        }else if(temp.railroadCar instanceof RCGaseous){
                            System.out.println("Railroad Car Gaseous " + temp.railroadCar.ID + "; total weight = " + ((RCGaseous) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCGaseous) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                        }else if(temp.railroadCar instanceof RCLiquidToxicMaterials){
                            System.out.println("Railroad Car Liquid Toxic Materials " + temp.railroadCar.ID + "; total weight = " + ((RCLiquidToxicMaterials) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCLiquidToxicMaterials) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection + "; security information = " + ((RCLiquidToxicMaterials) temp.railroadCar).securityInfo);
                        }else if(temp.railroadCar instanceof RCToxicMaterials){
                            System.out.println("Railroad Car Toxic Materials " + temp.railroadCar.ID + "; total weight = " + ((RCToxicMaterials) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCToxicMaterials) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection + "; security information = " + ((RCToxicMaterials) temp.railroadCar).securityInfo);
                        }
                        temp = temp.next;
                    }
                    menu();
                    return;
                }
            }
            System.out.println("No such a trainset with that index");
        }else if(x == 14){
            System.out.println("Select locomotive ID: ");
            for(int i = 0; i < trainsets.size(); i = i + 1){
                System.out.print(trainsets.get(i).locomotive.ID + " ");
            }
            System.out.println();
            x = in.nextInt();
            for(int i = 0; i < trainsets.size(); i = i + 1){
                if(x == trainsets.get(i).locomotive.ID){
                    System.out.println("Select Railroad car ID: ");
                    Node temp = trainsets.get(i).locomotive.head;
                    while(temp != null){
                        System.out.print(temp.railroadCar.ID + " ");
                        temp = temp.next;
                    }
                    System.out.println();
                    x = in.nextInt();
                    Node temp1 = trainsets.get(i).locomotive.head;
                    while(temp1 != null){
                        if(x == temp1.railroadCar.ID){
                            if(temp1.railroadCar instanceof RCRefrigerated){
                                System.out.println("Refrigerated RC: (1) cooling,(2) heating, (3)add some stuff,(4) remove some,(5)remove all, select:");
                                x = in.nextInt();
                                if(x == 1){
                                    ((RCRefrigerated) temp1.railroadCar).cooling();
                                }else if(x == 2){
                                    ((RCRefrigerated) temp1.railroadCar).heating();
                                }else if(x == 3){
                                    System.out.println("Write weight and volume of the selected item");
                                    double weight = in.nextDouble();
                                    double volume = in.nextDouble();
                                    ((RCRefrigerated) temp1.railroadCar).addSome(weight,volume);
                                }else if(x == 4){
                                    System.out.println("Write weight and volume of the selected item");
                                    double weight = in.nextDouble();
                                    double volume = in.nextDouble();
                                    ((RCRefrigerated) temp1.railroadCar).removeSome(weight,volume);
                                }else if(x == 5){
                                    ((RCRefrigerated) temp1.railroadCar).removeAll();
                                }else{
                                    System.out.println("There is no functionality like this");
                                }
                            }else if(temp1.railroadCar instanceof RCPassenger){
                                System.out.println("Passenger RC : (1)Add people,(2) remove some people,(3)remove all people,(4)reclineSeats ,(5) return back reclined seat to normal, select:");
                                x = in.nextInt();
                                if(x == 1){
                                    System.out.println("Write down person name and weight of the equipment");
                                    String name = in.next();
                                    double weight = in.nextDouble();
                                    ((RCPassenger) temp1.railroadCar).addPeople(new Person(name),new Equipment(weight));
                                }else if(x == 2){
                                    int rand = (int)(Math.random() * ((RCPassenger) temp1.railroadCar).numOfPeople);
                                    ((RCPassenger) temp1.railroadCar).removeSomePeople(rand);
                                }
                                else if(x == 3){
                                    ((RCPassenger) temp1.railroadCar).removeAllPeople();
                                }
                                else if(x == 4){
                                    ((RCPassenger) temp1.railroadCar).reclineSeats();
                                }
                                else if(x == 5){
                                    ((RCPassenger) temp1.railroadCar).returningBackToNormalCondition();
                                }else{
                                    System.out.println("There is no functionality like this");
                                }
                            }else if(temp1.railroadCar instanceof RCRestaurant){
                                System.out.println("There is only one option, so it runs automatically");
                                ((RCRestaurant) temp1.railroadCar).seeAndBuyFromMenu();
                            }else if(temp1.railroadCar instanceof RCBasicFreight){
                                System.out.println("Select from (1) remove all,(2) remove some and (3) add some");
                                x = in.nextInt();
                                if(x == 1){
                                    ((RCBasicFreight) temp1.railroadCar).removeAll();
                                }else if(x == 2){
                                    System.out.println("Write weight and volume of the selected item");
                                    double weight = in.nextDouble();
                                    double volume = in.nextDouble();
                                    ((RCBasicFreight) temp1.railroadCar).removeSome(weight,volume);
                                }else if(x == 3){
                                    System.out.println("Write weight and volume of the selected item");
                                    double weight = in.nextDouble();
                                    double volume = in.nextDouble();
                                    ((RCBasicFreight) temp1.railroadCar).addSome(weight,volume);
                                }else{
                                    System.out.println("There is no such an option");
                                }
                            }else if(temp1.railroadCar instanceof RCHeavyFreight){
                                System.out.println("As this items can be dangerous we can only (1) add at once or(2) remove all, select: ");
                                if(x == 1){
                                    System.out.println("Write weight and volume of the selected item");
                                    double weight = in.nextDouble();
                                    double volume = in.nextDouble();
                                    ((RCHeavyFreight) temp1.railroadCar).addAtOnce(weight,volume);
                                }else if(x == 2){
                                    ((RCHeavyFreight) temp1.railroadCar).removeAtOnce();
                                }else{
                                    System.out.println("There is no such an option");
                                }
                            }else{
                                System.out.println("There is no additional functionalities for this");
                            }
                            menu();
                            return;
                        }
                        temp1 = temp1.next;
                    }
                    System.out.println("There is no such a railroad car");
                    menu();
                    return;
                }
            }
            System.out.println("No such a locomotive");
        }else if(x == 15){
            File myObj = new File("AppState.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }else {
                System.out.println("Rewriting a file from new");
            }
            FileWriter file = new FileWriter("AppState.txt");
            MultithreadingDemo2 multithreadingDemo2 = new MultithreadingDemo2(trainsets,connection,file);
            multithreadingDemo2.start();
        }
        menu();
        return;
    }
}
