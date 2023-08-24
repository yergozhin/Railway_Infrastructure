import java.util.ArrayList;
import java.util.List;

public class GenerateTrainsets {
    List<Trainset> trainsets;
    public GenerateTrainsets(int numberOfTrainsets,List<Station> stations){
        trainsets = new ArrayList<>();
        for(int i = 0; i < numberOfTrainsets; i = i + 1){
            int rand1 = (int)(Math.random() * stations.size());
            int rand2 = (int)(Math.random() * stations.size());
            while (rand1 == rand2){
                rand2 = (int)(Math.random() * stations.size());;
            }
            double randSpeed = (double)(Math.random() * 80) + 130;
            trainsets.add(new Trainset(new Locomotive("L" + i,stations.get(rand1),stations.get(rand2),randSpeed)));
            int rand = (int)(Math.random() * 5) + 5;
            for(int j = 0; j <= rand; j = j + 1){
                int x = (int)(Math.random()*10);
                RailroadCar railroadCar = null;
                if(x == 0){
                    double random = (double)(Math.random() * 1000);
                    int rndm = (int)(Math.random() * 10000);
                    railroadCar = new RCPostOffice(rndm,random);
                }else if(x == 1){
                    double random = (double)(Math.random() * 1000);
                    int rndm = (int)(Math.random() * 70);
                    int lamps = (int)(Math.random() * 15);
                    railroadCar = new RCPassenger(rndm,random,lamps);
                }else if(x == 2){
                    double random = (double)(Math.random() * 1000);
                    int tables = (int)(Math.random() * 10);
                    int refr = (int)(Math.random() * 3) + 2;
                    railroadCar = new RCRestaurant(tables,refr,random);
                }else if(x == 3){
                    double degree = (double)(Math.random()*80)-40;
                    double weight = (double)(Math.random()*1000);
                    double volume = (double)(Math.random()*60);
                    railroadCar = new RCRefrigerated(degree,weight,volume);
                }else if(x == 4){
                    double weight = (double)(Math.random()*1000);
                    double volume = (double)(Math.random()*60);
                    String s1 = "FireDangerous";
                    String s2 = "Don'tAddWater";
                    String s3 = "Only with special suit";
                    int random = (int)(Math.random()*3)+1;
                    String s;
                    if (random == 1) {
                        s = s1;
                    }else if(random  == 2){
                        s = s2;
                    }else{
                        s = s3;
                    }
                    railroadCar = new RCLiquidToxicMaterials(weight,volume,s);
                }
                else if(x == 5){
                    double weight = (double)(Math.random()*1000);
                    double volume = (double)(Math.random()*60);
                    railroadCar = new RCLiquid(weight,volume);
                }else if(x == 6){
                    double weight = (double)(Math.random()*1000);
                    double volume = (double)(Math.random()*60);
                    railroadCar = new RCGaseous(weight,volume);
                }else if(x == 7){
                    double weight = (double)(Math.random()*1000);
                    double volume = (double)(Math.random()*60);
                    String s1 = "FireDangerous";
                    String s2 = "Don'tAddWater";
                    String s3 = "Only with special suit";
                    int random = (int)(Math.random()*3)+1;
                    String s;
                    if (random == 1) {
                        s = s1;
                    }else if(random  == 2){
                        s = s2;
                    }else{
                        s = s3;
                    }
                    railroadCar = new RCExplosives(weight,volume,s);
                }else if(x == 8){
                    double weight = (double)(Math.random()*1000);
                    double volume = (double)(Math.random()*60);
                    railroadCar = new RCBaggage(weight,volume);
                }else{
                    double weight = (double)(Math.random()*1000);
                    double volume = (double)(Math.random()*60);
                    String s1 = "FireDangerous";
                    String s2 = "Don'tAddWater";
                    String s3 = "Only with special suit";
                    int random = (int)(Math.random()*3)+1;
                    String s;
                    if (random == 1) {
                        s = s1;
                    }else if(random  == 2){
                        s = s2;
                    }else{
                        s = s3;
                    }
                    railroadCar = new RCToxicMaterials(weight,volume,s);
                }
                trainsets.get(trainsets.size() - 1).locomotive.addBack(railroadCar);
            }
        }
    }
}
