class MultithreadingDemo extends Thread { 
    Double speed;
    Trainset trainset;
    List<Stations> stations;
    List<Traiset> trainsets;
    List<Connections.Triplet> connection;
    MyQueueForLists myQueueForLists;
    Connection connections;
    double DistanceNow;
    double distanceFull;
    public MultithreadingDemo(Double speed, Trainset trainset,List<Stations> stations,List<Traiset> trainsets,List<Connections.Triplet> connection,MyQueueForLists myQueueForLists,Connection connections,double DistanceNow,double distanceFull){
        this.speed = speed;
        this.trainset = trainset;
        this.connection = connection;
        this.connections = connections;
        this.myQueueForLists = myQueueForLists;
        this.stations = stations;
        this.trainsets = trainsets;
        this.DistanceNow = DistanceNow;
        this.distanceFull = distanceFull;
    }
    public void run()
    {
        System.out.println("Working");
        if(speed >= 200) {
            try {
                throw new MultithreadingDemo.RailroadHazard("Locomotive " + trainset.locomotive.name + " exceeded speed of 200km/h");
            } catch (MultithreadingDemo.RailroadHazard e) {
                int x = trainset.locomotive.ID;
                for(int i = 0; i < trainsets.size(); i = i + 1){
                    if(trainsets.get(i).locomotive.ID == x){
                        System.out.println("Locomotive name: " + trainsets.get(i).locomotive.name + "; Home station: " + trainsets.get(i).locomotive.station0.name + "; Destination station: " + trainsets.get(i).locomotive.station1.name);
                        MyQueueForLists myQueueForLists1 = new MyQueueForLists();
                        boolean forward = true;
                        boolean check = false;
                        double currentDistance = DistanceNow;
                        while(!myQueueForLists.empty()){
                            PairForListAndLoco pair = myQueueForLists.dequeue();
                            if(pair.trainset.locomotive.ID == x){
                                currentDistance = pair.distance;
                                forward = pair.forward;
                                check = true;
                            }
                            myQueueForLists1.enqueue(pair);
                        }
                        double totalDistance = 0;
                        while (!myQueueForLists1.empty()){
                            PairForListAndLoco pair = myQueueForLists1.dequeue();
                            if(pair.trainset.locomotive.ID == x){
                                totalDistance = 0;
                                for(int k = 1; k < pair.list.size(); k = k + 1){
                                    int st0 = pair.list.get(k - 1).ID;
                                    int st1 = pair.list.get(k).ID;
                                    for(int j = 0; j < connection.list.size(); j = j + 1){
                                        int cn0 = connections.list.get(j).station0.ID;
                                        int cn1 = connections.list.get(j).station1.ID;
                                        if((st0 == cn0 && st1 == cn1) || (st1 == cn0 && st0 == cn1)){
                                            totalDistance = totalDistance + connection.list.get(j).distance;
                                            break;
                                        }
                                    }
                                }
                            }
                            myQueueForLists.enqueue(pair);
                        }
                        //System.out.println(currentDistance);
                        //System.out.println(totalDistance);
                        currentDistance = (currentDistance/totalDistance);
                        currentDistance = currentDistance*100;
                        if(!check){
                            System.out.println("This trainset is not in the route");
                        }else if(forward){
                            System.out.println("Goes from home station until destination station with the " + currentDistance +  "% completed distance");
                        }else if(!forward){
                            System.out.println("Goes from destination station until home station with the " + currentDistance +  "% completed distance");
                        }
                        Node temp = trainsets.get(i).locomotive.head;
                        while(temp != null){
                            if(temp.railroadCar instanceof RCPassenger){
                                System.out.println("Railroad Car Passenger " + temp.railroadCar.ID + ": number of people = " + ((RCPassenger) temp.railroadCar).numOfPeople + "; weight of the transported equipment = " + ((RCPassenger) temp.railroadCar).EqWeight + "; number of lamps = " + ((RCPassenger) temp.railroadCar).numOfLamps + "; number of seats = " + ((RCPassenger) temp.railroadCar).numOfSeats + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                            }else if(temp.railroadCar instanceof RCRestaurant){
                                System.out.println("Railroad Car Restaurant " + temp.railroadCar.ID + ": number of refrigerators = " + ((RCRestaurant) temp.railroadCar).numOfRefrigerators + "; number of tables = " + ((RCRestaurant) temp.railroadCar).numOfTables + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                            }else if(temp.railroadCar instanceof RCRefrigerated){
                                System.out.println("Railroad Car Refrigerator " + temp.railroadCar.ID + ": total weight = " + ((RCRefrigerated) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCRefrigerated) temp.railroadCar).curTotalVolume + "; cooling = " + ((RCRefrigerated) temp.railroadCar).cling + "; heating = " + ((RCRefrigerated) temp.railroadCar).hting + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                            }else if(temp.railroadCar instanceof RCPostOffice){
                                System.out.println("Railroad Car Post Office " + temp.railroadCar.ID + ": number of mailboxes =  " + ((RCPostOffice) temp.railroadCar).numOfMailboxes + "; total weight = " + temp.railroadCar.weight + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                            }else if(temp.railroadCar instanceof RCBaggage){
                                System.out.println("Railroad Car Baggage " + temp.railroadCar.ID + "; total weight = " + ((RCBaggage) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCBaggage) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                            }else if(temp.railroadCar instanceof RCLiquid){
                                System.out.println("Railroad Car Liquid " + temp.railroadCar.ID + "; total weight = " + ((RCLiquid) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCLiquid) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                            }else if(temp.railroadCar instanceof RCExplosives){
                                System.out.println("Railroad Car Explosives " + temp.railroadCar.ID + "; total weight = " + ((RCExplosives) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCExplosives) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection + "; security information = " + ((RCExplosives) temp.railroadCar).securityInfo);
                            }else if(temp.railroadCar instanceof RCGaseous){
                                System.out.println("Railroad Car Gaseous " + temp.railroadCar.ID + "; total weight = " + ((RCGaseous) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCGaseous) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection);
                            }else if(temp.railroadCar instanceof RCLiquidToxicMaterials){
                                System.out.println("Railroad Car Liquid Toxic Materials " + temp.railroadCar.ID + "; total weight = " + ((RCLiquidToxicMaterials) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCLiquidToxicMaterials) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection + "; security information = " + ((RCLiquidToxicMaterials) temp.railroadCar).securityInfo);
                            }else if(temp.railroadCar instanceof RCToxicMaterials){
                                System.out.println("Railroad Car Toxic Materials " + temp.railroadCar.ID + "; total weight = " + ((RCToxicMaterials) temp.railroadCar).curTotalWeight + "; total volume = " + ((RCToxicMaterials) temp.railroadCar).curTotalVolume + "; electricity grid connection = " + temp.railroadCar.electricalGrid.connection + "; security information = " + ((RCToxicMaterials) temp.railroadCar).securityInfo);
                            }
                            temp = temp.next;
                        }
                        System.out.println("Distance completed on the route = " + ((DistanceNow/distanceFull)*100));
                        break;
                    }
                }
                throw new RuntimeException(e);
            }
        }
    }

    public static class RailroadHazard extends Throwable {
        public RailroadHazard(String s) {
            super(s);
        }
    }
}
