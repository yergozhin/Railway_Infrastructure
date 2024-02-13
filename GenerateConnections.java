import java.util.List; 

public class GenerateConnections {
    public GenerateConnections(int numberOfConnectionsForEachStation, List<Station> station, Connections connections){
        for(int i = 0; i < station.size(); i = i + 1){
            for(int j = 0; j < numberOfConnectionsForEachStation; j = j + 1){
                int rand = (int)(Math.random() * station.size());
                while(rand == i){
                    rand = (int)(Math.random() * station.size());
                }
                Double randomDistance = (double)(Math.random() * 1500) + 500;
                connections.makeConnection(station.get(i),station.get(rand),randomDistance);
                connections.makeConnection(station.get(rand),station.get(i),randomDistance);
            }
        }
    }
}
