package ships;

public class Battleship extends Ship {
    public final int size = 4;
    private boolean destroyed;

    public Battleship(int x, int y, String orientation){
        super(x, y, orientation);
    }
    public boolean destroyed(){
        return destroyed;
    }
    public int id(){
        return 4;
    }
    public int size(){
        return size;
    }
}
