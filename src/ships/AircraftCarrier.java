package ships;

public class AircraftCarrier extends Ship {
    public final int size = 5;
    private boolean destroyed;


    public AircraftCarrier(int x, int y, String orientation){
        super(x, y, orientation);
    }
    public boolean destroyed(){
        return destroyed;
    }
    public int id(){
        return 5;
    }
    public int size(){
        return size;
    }


}
