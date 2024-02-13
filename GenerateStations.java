import java.util.ArrayList;
import java.util.List; 

public class GenerateStations {
    List<Station> stations;
    public GenerateStations(int numberOfStations){
        stations = new ArrayList<>();
        for(int i = 0; i < numberOfStations; i = i + 1){
            stations.add(new Station("S" + i));
        }
    }
}
