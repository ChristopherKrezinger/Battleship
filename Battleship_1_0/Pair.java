package Battleship_1_0;



//used to make pairs of different generic types
public class Pair {
    private Object first;
    private Object second;

    public Pair (Object first, Object second){
        this. first = first;
        this.second = second;
    }
    public Object getFirst(){
        return first;
    }

    public Object getSecond(){
        return second;
    }

    public void setFirst(Object first){
        this.first = first;
    }

    public void setSecond(Object second){
        this.second = second;
    }
}
