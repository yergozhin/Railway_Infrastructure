import java.util.ArrayList;
import java.util.List;

public class Station {
    String name;
    static int index = 0;
    int ID;
    static List<Station> stations = new ArrayList<>();
    public Station(String name){
        this.name = name;
        this.ID = index; 
        index++;
    };
}
