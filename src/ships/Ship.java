package ships;

import java.util.Map;

public abstract class Ship{
    private boolean destroyed;
    protected int startingX, startingY;

    public Ship(int x, int y, String orientation){
        this.destroyed = false;
        this.startingX = x;
        this.startingY = y;
        this.orientation = orientation;
    }
    public boolean isDestroyed(){
        return destroyed;
    }
    private int size;
    public int size(){
        return size;
    }
    private int id;
    public int id(){
        return id;
    }
    protected String orientation;

    public String getOrientation() {
        return orientation;
    }

    public int getStartingX() {
        return startingX;
    }

    public int getStartingY() {
        return startingY;
    }


    //for debugging
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
