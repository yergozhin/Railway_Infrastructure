import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main { 
    public static void main(String[] args) throws InterruptedException, IOException {
        GenerateStations generateStations = new GenerateStations(100);
        Connections connections = new Connections();
        GenerateConnections generateConnections = new GenerateConnections(1,generateStations.stations,connections);
        GenerateTrainsets generateTrainsets = new GenerateTrainsets(25,generateStations.stations);
        for(int i = 0; i < connections.list.size(); i = i + 1){
            System.out.println(connections.list.get(i).station0.ID + " " + connections.list.get(i).station1.ID + " " + connections.list.get(i).distance);
        }
        GeneratePath generatePath = new GeneratePath(connections,generateTrainsets.trainsets,generateStations.stations);
        MyQueueForLists myQueueForLists = new MyQueueForLists();
        for(int i = 0; i < generatePath.list.size(); i = i + 1){
            System.out.println(generateTrainsets.trainsets.get(i).locomotive.name + " " + generateTrainsets.trainsets.get(i).locomotive.station0.ID + " " + generateTrainsets.trainsets.get(i).locomotive.station1.ID);
            if(generatePath.list.get(i).get(0).name == "noPath"){
                //System.out.println("There is no path");
                continue;
            }
            PairForListAndLoco pair = new PairForListAndLoco(generateTrainsets.trainsets.get(i),generatePath.list.get(i),1,true,0);
            myQueueForLists.enqueue(pair);
            for (int j = 0; j < generatePath.list.get(i).size(); j = j + 1){
                if(j > 0){
                    for(int k = 0; k < connections.list.size(); k = k + 1){
                        int one = connections.list.get(k).station0.ID;
                        int two = connections.list.get(k).station1.ID;
                        int three = generatePath.list.get(i).get(j - 1).ID;
                        int four = generatePath.list.get(i).get(j).ID;
                        if((one == three && two == four) || (one == four && two == three)){
                            System.out.print(" d" + connections.list.get(k).distance + " ");
                            break;
                        }
                    }
                }
                //System.out.print(generatePath.list.get(i).get(j).ID + " ");
            }
            System.out.println();
        }
        //Run run = new Run(myQueueForLists,connections);
        Menu menu = new Menu(generateStations.stations,generateTrainsets.trainsets,connections.list,myQueueForLists,connections);
        /*List<Station> stations = new ArrayList<>();
        List<Trainset> trainsets = new ArrayList<>();
        List<Connections.Triplet> list = new ArrayList<>();
        Connections connections = new Connections();*/
        menu.menu();
        /*RCRestaurant railroadCar = new RCRestaurant(5,3,1000);
        railroadCar.seeAndBuyFromMenu();*/
    }
}
