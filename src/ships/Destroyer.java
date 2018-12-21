package ships;

public class Destroyer extends Ship {
    public final int size = 2;
    private boolean destroyed;

    public Destroyer(int x, int y, String orientation){
        super(x, y, orientation);
    }
    public boolean destroyed(){
        return destroyed;
    }
    public int id(){
        return 2;
    }
    public int size(){
        return size;
    }

}
