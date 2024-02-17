import java.util.List;

public class PairForListAndLoco {
    Trainset trainset;
    List<Station> list;
    int i; 
    boolean forward;
    double distance;
    public PairForListAndLoco(Trainset trainset, List<Station> list, int i, boolean forward,double distance){
        this.trainset = trainset;
        this.list = list;
        this.i = i;
        this.forward = forward;
        this.distance = distance;
    }
}
