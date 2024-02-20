import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

public class Presentation {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Creating stations
        Station station1 = new Station("St1");
        Station station2 = new Station("St2");
        //Creating connection between those two stations
        Connections connections = new Connections();
        connections.makeConnection(station1,station2,1000);
        //Creating locomotive
        Locomotive locomotive = new Locomotive("Loco",station1,station2,143);
        //Creating trainset
        Trainset trainset = new Trainset(locomotive);
        //Creating railroad cars
        RailroadCar railroadCar = new RailroadCar();
        RCRefrigerated rcRefrigerated = new RCRefrigerated(-20,1000,51);
        RCPassenger rcPassenger = new RCPassenger(50,1400,8);
        RCRestaurant rcRestaurant = new RCRestaurant(5,3,300);
        //and so on...
        //Showing functionalities of railroads cars
        //Only three Railroad car types have functionalities which are not getters or setters
        //These two functions of a refrigerated railroad car type will check whether your
        //railroad car needs to be colder or hotter, and if you used correct function it will make it
        rcRefrigerated.heating();
        rcRefrigerated.cooling();
        //These functions of a passenger railroad car adds people and removes people,also gives us a
        //chance to recline our seats and return them back to their normal form
        rcPassenger.reclineSeats();
        rcPassenger.returningBackToNormalCondition();
        rcPassenger.addPeople(new Person("John"),new Equipment(100));
        rcPassenger.removeSomePeople(0);
        rcPassenger.removeAllPeople();
        //These function of a restaurant railroad car gives us a chance to buy some food and drinks
        //from the menu
        rcRestaurant.seeAndBuyFromMenu();
        //Adding a railroad car to locomotive
        locomotive.addBack(railroadCar);
        locomotive.addBack(rcPassenger);
        //Creating a path for locomotive from home station until destination station
        List<Station> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);
        List<Station> listPath;
        Path path = new Path();
        listPath = path.makePath(connections,locomotive.station0,locomotive.station1,stations);
        //Creating queue of trainsets waiting for their way
        MyQueueForLists myQueueForLists = new MyQueueForLists();
        myQueueForLists.enqueue(new PairForListAndLoco(trainset,listPath,1,true,0));
        //Running the queue and after each route inputting the value 5 to continue the path of each locomotive
        List<Trainset> trainsets = new ArrayList<>();
        trainsets.add(trainset);
        Run run = new Run(myQueueForLists,connections,stations,trainsets,connections.list);
        //Additionally generating 100 stations, 25 trainsets with some railroads, connections between
        //stations and paths of each locomotive
        GenerateStations generateStations = new GenerateStations(100);
        GenerateTrainsets generateTrainsets = new GenerateTrainsets(25,generateStations.stations);
        GenerateConnections generateConnections = new GenerateConnections(1,generateStations.stations,connections);
        GeneratePath generatePath = new GeneratePath(connections,generateTrainsets.trainsets,generateStations.stations);

    }
}
