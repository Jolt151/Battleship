package ships;

public class Submarine extends Ship{
    public final int size = 1;
    private boolean destroyed;

    public Submarine(int x, int y, String orientation){
        super(x, y, orientation);
    }
    public boolean destroyed(){
        return destroyed;
    }
    public int id(){
        return 1;
    }
    public int size(){
        return size;
    }

}
