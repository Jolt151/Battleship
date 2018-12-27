import ships.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerBoard extends Board {

    //store all the ships so we can easily see what ships still exist
    //use arraylist so we can store multiple of the same ship
    private ArrayList<Ship> ships;

    public PlayerBoard(){
        super();
        ships = new ArrayList<>();
    }

    //returns true if the ship was successfully placed
    //returns false if the ship will not fit in that position
    public boolean placeShip(Ship ship){
        //x and y for matrices are backwards

        int shipSize = ship.size();
        int x = ship.getStartingX();
        int y = ship.getStartingY();
        String orientation = ship.getOrientation();

        //if the ship doesn't fit, return false
        if (orientation.equals(Constants.VERTICAL) && (y + shipSize) > size) return false;
        if (orientation.equals(Constants.HORIZONTAL) && (x + shipSize) > size) return false;

        //check to see if the ship will go through any other ship before we place it
        for (int i = 0; i < shipSize; i++) {
            if (orientation.equals(Constants.VERTICAL) && theBoard[y+i][x] > 0) return false;
            else if (orientation.equals(Constants.HORIZONTAL) && theBoard[y][x+i] > 0) return false;
        }

        for (int i = 0; i < shipSize; i++){
            //for simplicity's sake, we will have the user select the starting point for horizontal ships from the left
            //and vertical ships from the bottom
            if (orientation.equals(Constants.VERTICAL)){
                //input's y value stays the same, x value increments
                theBoard[y+i][x] = ship.id();
            } else if (orientation.equals(Constants.HORIZONTAL)){
                //input's y value stays the same, x value increments
                theBoard[y][x+i] = ship.id();
            }
            else throw new IllegalArgumentException("The ship must be vertical or horizontal.");
        }
        ships.add(ship);
        return true;
    }

    //used for attacking ships
    public boolean containsShipAt(int x, int y){
        //x and y for matrices are backwards
        if (theBoard[y][x] > 0){
            return true;
        }
        else return false;
    }

    public boolean specificShipExistsAt(Ship ship, int x, int y){
        int startingX = ship.getStartingX();
        int startingY = ship.getStartingY();
        String orientation = ship.getOrientation();
        int size = ship.size();
        if (orientation.equals(Constants.VERTICAL) && x == startingX && startingY + size >= y && startingY >= y){
            //if its vertical, x is the same, and startingY <= y <= startingy+size, the point is contained
            return true;
        }
        else if (orientation.equals(Constants.HORIZONTAL) && y == startingY && startingX + size >= x && x > startingX){
            return true;
        }
        else return false;
    }
    public void removeFromBoard(Ship ship){
        System.out.println("removing from board! previous: ");
        print2D(theBoard);

        int startingX = ship.getStartingX();
        int startingY = ship.getStartingY();
        String orientation = ship.getOrientation();
        int size = ship.size();
        for (int i = 0; i < ships.size(); i++){
            if (ships.get(i).getStartingX() == startingX && ships.get(i).getStartingY() ==startingY){
                ships.remove(i);
                break;
            }
        }

        if (orientation.equals(Constants.VERTICAL)){
            //our y axis is changing, x is staying the same
            for (int i = startingY; i < size + startingY; i++){ //TODO verify the -1
                theBoard[i][startingX] = 0;
            }
        }

        else if (orientation.equals(Constants.HORIZONTAL)){
            //x axis is changing, y is staying the same
            for (int i = startingX; i < size + startingX; i++){
                theBoard[startingY][i] = 0;
            }
        }

    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
    public static void print2D(int[][] arr){
        for (int i = 0; i <arr.length; i++){
            for (int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
