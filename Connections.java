import java.util.ArrayList;
import java.util.List;

public class Connections {
    List<Triplet> list;
    public Connections(){
        this.list = new ArrayList<>();
    }
    public void makeConnection(Station station0, Station station1,double distance){
        Triplet triplet = new Triplet(station0,station1,distance);
        list.add(triplet);
    }
    public static class Triplet {
        Station station0;
        Station station1;
        double distance;
        public Triplet(Station station0, Station station1, double distance){
            this.station0 = station0;
            this.station1 = station1;
            this.distance = distance;
        }
    }
}
