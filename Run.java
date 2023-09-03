import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run extends Thread{
    public Run(MyQueueForLists queue, Connections connections,List<Station> stations,List<Trainset> trainsets,List<Connections.Triplet> connection) throws InterruptedException, IOException {
        MyQueueForLists temp = new MyQueueForLists();
        List<PairForListAndLoco> myList = new ArrayList<>();
        while(!queue.empty()){
            PairForListAndLoco route = queue.dequeue();
            myList.add(route);
        }
        for(int i = 0; i < myList.size(); i = i + 1){
            boolean alone = true;
            for(int j = i + 1; j < myList.size(); j = j + 1){
                if((myList.get(j).list.get(0) == myList.get(i).list.get(0) && myList.get(j).list.get(1) == myList.get(i).list.get(1)) || (myList.get(j).list.get(0) == myList.get(i).list.get(1) && myList.get(j).list.get(1) == myList.get(i).list.get(0))){
                    temp.enqueue(myList.get(i));
                    alone = false;
                    break;
                }
            }
            if(alone){
                queue.enqueue(myList.get(i));
            }
        }
        while (!queue.empty()){
            PairForListAndLoco pair = queue.dequeue();
            Station begin;
            Station end;
            boolean checking = false;
            MyQueueForLists queuee1 = new MyQueueForLists();
            if (pair.forward) {
                begin = pair.list.get(pair.i - 1);
                end = pair.list.get(pair.i);
                boolean check = false;
                while (!temp.empty()){
                    PairForListAndLoco route1 = temp.dequeue();
                    int i = -1;
                    int j = -1;
                    if(route1.forward){
                        i = route1.list.get(route1.i - 1).ID;
                        j = route1.list.get(route1.i).ID;
                    }else{
                        i = route1.list.get(route1.i + 1).ID;
                        j = route1.list.get(route1.i).ID;
                    }
                    if(!check && ((begin.ID == i && end.ID == j) || (begin.ID == j && end.ID == i))){
                        queue.enqueue(route1);
                        check = true;
                    }else{
                        queuee1.enqueue(route1);
                    }
                }
            } else {
                begin = pair.list.get(pair.i + 1);
                end = pair.list.get(pair.i);
                boolean check = false;
                while (!temp.empty()){
                    PairForListAndLoco route1 = temp.dequeue();
                    int i = -1;
                    int j = -1;
                    if(route1.forward){
                        i = route1.list.get(route1.i - 1).ID;
                        j = route1.list.get(route1.i).ID;
                    }else{
                        i = route1.list.get(route1.i + 1).ID;
                        j = route1.list.get(route1.i).ID;
                    }
                    if(!check && ((begin.ID == i && end.ID == j) || (begin.ID == j && end.ID == i))){
                        queue.enqueue(route1);
                        check = true;
                    }else{
                        queuee1.enqueue(route1);
                    }
                }
            }
            Station start = begin;
            Station finish = end;
            while (!queuee1.empty()){
                PairForListAndLoco route1 = queuee1.dequeue();
                temp.enqueue(route1);
            }
            double distance = 0;
            double copy_distance = 0;
            for(int i = 0; i < connections.list.size(); i = i + 1){
                if((begin.ID == connections.list.get(i).station0.ID && end.ID == connections.list.get(i).station1.ID) || (end.ID == connections.list.get(i).station0.ID && begin.ID == connections.list.get(i).station1.ID)){
                    distance = connections.list.get(i).distance;
                    copy_distance = distance;
                }
            }
            double fullDistance = distance;
            System.out.println(pair.trainset.locomotive.name + " goes from station " + begin.name + " until station " + end.name);
            double speed = pair.trainset.locomotive.speed;
            double maxSpeed = 0;
            while(distance > 0){
                System.out.println("Distance = " + distance + " ; Speed = " + speed);
                if(speed >= 200){
                    MultithreadingDemo object = new MultithreadingDemo(maxSpeed,pair.trainset,stations,trainsets,connection,queue,connections,distance,fullDistance);
                    object.start();
                }
                distance = distance - speed;
                maxSpeed = Math.max(maxSpeed,speed);
                double random = (double) (Math.random() * 2);
                if(random < 1){
                    speed = speed + (speed*0.03);
                }else{
                    speed = speed - (speed*0.03);
                }
                Thread.sleep(1000);
            }
            //MultithreadingDemo object = new MultithreadingDemo(maxSpeed,pair.trainset);
            //object.start();
            if(pair.i == pair.list.size() - 1 || pair.i == 0){
                if(pair.i == pair.list.size() - 1){
                    boolean see = false;
                    PairForListAndLoco route1 = new PairForListAndLoco(pair.trainset,pair.list, pair.i - 1, false,0);
                    begin = route1.list.get(route1.i + 1);
                    end = route1.list.get(route1.i);
                    boolean check1 = false;
                    while (!queue.empty()){
                        PairForListAndLoco route2 = queue.dequeue();
                        int i = -1;
                        int j = -1;
                        if(route2.forward){
                            i = route2.list.get(route2.i - 1).ID;
                            j = route2.list.get(route2.i).ID;
                        }else{
                            i = route2.list.get(route2.i + 1).ID;
                            j = route2.list.get(route2.i).ID;
                        }
                        if(!check1 && ((i == begin.ID && j == end.ID) || (i == end.ID && j == begin.ID))){
                            check1 = true;
                        }
                        queuee1.enqueue(route2);
                    }
                    if(check1){
                        while(!queuee1.empty()){
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        temp.enqueue(route1);
                    }else {
                        while(!queuee1.empty()){
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        queue.enqueue(route1);
                    }
                    System.out.println(pair.trainset.locomotive.name + " reached its destination station " + pair.trainset.locomotive.station1.name);
                }else{
                    boolean see = false;
                    PairForListAndLoco route1 = new PairForListAndLoco(pair.trainset, pair.list, pair.i + 1, true,0);
                    begin = route1.list.get(route1.i - 1);
                    end = route1.list.get(route1.i);
                    boolean check1 = false;
                    while (!queue.empty()){
                        PairForListAndLoco route2 = queue.dequeue();
                        int i = -1;
                        int j = -1;
                        if(route2.forward){
                            i = route2.list.get(route2.i - 1).ID;
                            j = route2.list.get(route2.i).ID;
                        }else{
                            i = route2.list.get(route2.i + 1).ID;
                            j = route2.list.get(route2.i).ID;
                        }
                        if(!check1 && ((i == begin.ID && j == end.ID) || (i == end.ID && j == begin.ID))){
                            check1 = true;
                        }
                        queuee1.enqueue(route2);
                    }
                    if(check1){
                        while(!queuee1.empty()){
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        temp.enqueue(route1);
                    }else {
                        while(!queuee1.empty()){
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        queue.enqueue(route1);
                    }
                    System.out.println(pair.trainset.locomotive.name + " reached its home station " + pair.trainset.locomotive.station0.name);
                }
                Thread.sleep(30000);
            }else {
                if (pair.forward) {
                    boolean see = false;
                    PairForListAndLoco route1 = new PairForListAndLoco(pair.trainset, pair.list, pair.i + 1, true,(copy_distance + pair.distance));
                    begin = route1.list.get(route1.i - 1);
                    end = route1.list.get(route1.i);
                    boolean check1 = false;
                    while (!queue.empty()) {
                        PairForListAndLoco route2 = queue.dequeue();
                        int i = -1;
                        int j = -1;
                        if (route2.forward) {
                            i = route2.list.get(route2.i - 1).ID;
                            j = route2.list.get(route2.i).ID;
                        } else {
                            i = route2.list.get(route2.i + 1).ID;
                            j = route2.list.get(route2.i).ID;
                        }
                        if (!check1 && ((i == begin.ID && j == end.ID) || (i == end.ID && j == begin.ID))) {
                            check1 = true;
                        }
                        queuee1.enqueue(route2);
                    }
                    if (check1) {
                        while (!queuee1.empty()) {
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        temp.enqueue(route1);
                    } else {
                        while (!queuee1.empty()) {
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        queue.enqueue(route1);
                    }
                    System.out.println(pair.trainset.locomotive.name + " reached station " + finish.name + "; Next station is " + pair.list.get(pair.i + 1).name);
                } else {
                    boolean see = false;
                    PairForListAndLoco route1 = new PairForListAndLoco(pair.trainset, pair.list, pair.i - 1, false,(copy_distance + pair.distance));
                    start = route1.list.get(route1.i + 1);
                    end = route1.list.get(route1.i);
                    boolean check1 = false;
                    while (!queue.empty()){
                        PairForListAndLoco route2 = queue.dequeue();
                        int i = -1;
                        int j = -1;
                        if(route2.forward){
                            i = route2.list.get(route2.i - 1).ID;
                            j = route2.list.get(route2.i).ID;
                        }else{
                            i = route2.list.get(route2.i + 1).ID;
                            j = route2.list.get(route2.i).ID;
                        }
                        if(!check1 && ((i == start.ID && j == end.ID) || (i == end.ID && j == start.ID))){
                            check1 = true;
                        }
                        queuee1.enqueue(route2);
                    }
                    if(check1){
                        while(!queuee1.empty()){
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        temp.enqueue(route1);
                    }else {
                        while(!queuee1.empty()){
                            PairForListAndLoco route2 = queuee1.dequeue();
                            queue.enqueue(route2);
                        }
                        queue.enqueue(route1);
                    }
                    System.out.println(pair.trainset.locomotive.name + " reached station " + finish.name + "; Next station is " + pair.list.get(pair.i - 1).name);
                }
                Thread.sleep(2000);
            }
            Menu menu = new Menu(stations,trainsets,connection,queue,connections);
            menu.menu();
            Run run = new Run(queue,connections,stations,trainsets,connection);
            run.start();
        }
    }
}
