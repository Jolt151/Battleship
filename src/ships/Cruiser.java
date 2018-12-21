package ships;

public class Cruiser extends Ship {
    public final int size = 3;
    private boolean destroyed;

    public Cruiser(int x, int y, String orientation){
        super(x, y, orientation);
    }
    public boolean destroyed(){
        return destroyed;
    }
    public int id(){
        return 3;
    }
    public int size(){
        return size;
    }

}
