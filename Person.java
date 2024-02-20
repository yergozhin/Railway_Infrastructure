public class Person {
    String name;
    static int count = 0; 
    int ID;
    public Person(String name){
        this.name = name;
        this.ID = count;
        this.count++;
    }
}
