import java.util.ArrayList;
import java.util.List;

public class GeneratePath {
    List<List<Station>> list;
    public GeneratePath(Connections connections, List<Trainset> listTrainset, List<Station> listStation){
        this.list = new ArrayList<>();
        for(int i = 0; i < listTrainset.size(); i = i + 1){
            list.add(new Path().makePath(connections,listTrainset.get(i).locomotive.station0,listTrainset.get(i).locomotive.station1,listStation));
            listTrainset.get(i).locomotive.list = list.get(list.size() - 1);
        }
    }
}
