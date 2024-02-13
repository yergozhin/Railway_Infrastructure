public class ElectricalGrid {
    boolean connection;
    public ElectricalGrid(boolean connection){
        this.connection = connection;
    }
    public void disconnect(){
        this.connection = false;
    }
    public void connect(){
        this.connection = true;
    }
}
 
