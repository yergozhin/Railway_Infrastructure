public class Equipment {
    static int count = 0;
    double weight;
    int ID;
    public Equipment(double weight){
        this.weight = weight;
        ID = count;
        count++;
    }
}
