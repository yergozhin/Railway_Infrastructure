import java.util.ArrayList;
import java.util.List; 

public class Path {
    public Path(){}
    public List<Station> makePath(Connections connections, Station station0, Station station1, List<Station> list){
        List<List<Station>> edges = new ArrayList<>();
        int siz = 0;
        for(int i = 0; i < list.size(); i = i + 1){
            siz = Math.max(siz,list.get(i).ID);
        }
        siz = siz + 2;
        for(int i = 0; i < siz; i = i + 1){
            edges.add(new ArrayList<>());
        }
        for(int i = 0; i < connections.list.size(); i = i + 1){
            if(connections.list.get(i).distance > 0) {
                edges.get(connections.list.get(i).station0.ID).add(connections.list.get(i).station1);
                edges.get(connections.list.get(i).station1.ID).add(connections.list.get(i).station0);

            }
        }
        boolean[] visited = new boolean[siz];
        MyQueue myQueue = new MyQueue();
        Pair pair = new Pair(station0,new ArrayList<Station>());
        myQueue.enqueue(pair);
        while(!myQueue.empty()){
            Pair pair1 = myQueue.dequeue();
            Station station = pair1.station;
            if(visited[station.ID]){
                continue;
            }
            pair1.list.add(station);
            if(station.ID == station1.ID){
                return pair1.list;
            }
            visited[station.ID] = true;
            for(int i = 0; i < edges.get(station.ID).size(); i = i + 1){
                List<Station> newList = new ArrayList<>();
                for(int k = 0; k < pair1.list.size(); k = k + 1){
                    newList.add(pair1.list.get(k));
                }
                Pair pair2 = new Pair(edges.get(station.ID).get(i),newList);
                myQueue.enqueue(pair2);
            }
        }
        List<Station> list1 = new ArrayList<>();
        list1.add(new Station("noPath"));
        return list1;
    }
}
