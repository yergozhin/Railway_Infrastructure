import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultithreadingDemo2 extends Thread{
    List<Trainset> list;
    Connections connection; 
    FileWriter file;
    public MultithreadingDemo2(List<Trainset> list,Connections connection,FileWriter file){
        this.list = list;
        this.connection = connection;
        this.file = file;
    }
    public void run()
    {
        try {
            try {
                Map<Integer,Integer> map = new HashMap<>();
                for(int i = 0; i < list.size(); i = i + 1){
                    map.put(i,0);
                }
                for(int d = 0; d < list.size(); d = d + 1){
                    double maxDistance = -1;
                    int index = -1;
                    for(int i = 0; i < list.size(); i = i + 1){
                        double totalDistance = 0;
                        for (int k = 1; k < list.get(i).locomotive.list.size(); k = k + 1) {
                            int st0 = list.get(i).locomotive.list.get(k - 1).ID;
                            int st1 = list.get(i).locomotive.list.get(k).ID;
                            for (int j = 0; j < connection.list.size(); j = j + 1) {
                                int cn0 = connection.list.get(j).station0.ID;
                                int cn1 = connection.list.get(j).station1.ID;
                                if ((st0 == cn0 && st1 == cn1) || (st1 == cn0 && st0 == cn1)) {
                                    totalDistance = totalDistance + connection.list.get(j).distance;
                                    break;
                                }
                            }
                        }
                        if(map.get(i) == 0){
                            if(totalDistance > maxDistance){
                                maxDistance = totalDistance;
                                index = i;
                            }
                        }
                    }
                    map.put(index,1);
                    file.write("Locomotive: " + list.get(index).locomotive.name + "; distance: " + maxDistance + "\n"
                                    + "Railroad car type and weight: ");
                    Map<Integer,Integer> mp = new HashMap<>();
                    Node save = list.get(index).locomotive.head;
                    while(save != null){
                        mp.put(save.railroadCar.ID,0);
                        save = save.next;
                    }
                    Node head = list.get(index).locomotive.head;
                    while(head != null){
                        int min = 1000000000;
                        int ind = -1;
                        Node temp = head;
                        String railroadCarType = "";
                        while (temp != null){
                            if(mp.get(temp.railroadCar.ID) == 0){
                                if(temp.railroadCar instanceof RCPassenger){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCPassenger) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCRestaurant){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCRestaurant) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCRefrigerated){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCRefrigerated) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCPostOffice){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCPostOffice) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCBaggage){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCBaggage) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCLiquid){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCLiquid) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCExplosives){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCExplosives) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCGaseous){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCGaseous) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCLiquidToxicMaterials){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCLiquidToxicMaterials) temp.railroadCar).name;
                                    }
                                }else if(temp.railroadCar instanceof RCToxicMaterials){
                                    if(min > temp.railroadCar.weight){
                                        min = temp.railroadCar.weight;
                                        ind = temp.railroadCar.ID;
                                        railroadCarType = ((RCToxicMaterials) temp.railroadCar).name;
                                    }
                                }
                            }
                            temp = temp.next;
                        }
                        if(ind != -1){
                            mp.put(ind,1);
                            file.write(railroadCarType + ", " + min + "; ");
                        }
                        head = head.next;
                    }
                    file.write("\n");
                }
                file.write("\n");
                file.write("\nLast updated version\n");
                //file.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            Thread.sleep(5000);
            MultithreadingDemo2 multithreadingDemo2 =  new MultithreadingDemo2(list,connection,file);
            multithreadingDemo2.start();
        }
        catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}
