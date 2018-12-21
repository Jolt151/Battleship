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
        if (orientation.equals(Constants.VERTICAL) && (y + shipSize) >= size) return false;
        if (orientation.equals(Constants.HORIZONTAL) && (x + shipSize) >= size) return false;

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
    public int shipAt(int x, int y){
        //x and y for matrices are backwards
        if (x < 0 || x > size-1 || y < 0 || y > size - 1) return -1;
        return theBoard[y][x];
    }

        //NOT NEEDED ANYMORE
/*    public boolean destroyShip(int x, int y){
        if (containsShipAt(x, y)){
            int id = shipAt(x, y);

            //remove from arraylist
            for (int i = 0; i < ships.size(); i++){
                if (ships.get(i).id() == id) ships.remove(ships.get(i));
            }
            //TODO: remove from board
            //go until hit a new element or end of board?
            //corner cases:
            //two subs or destroyers side by side
            //two subs or destroyers perpendicular to each other
            //we can handle these corner cases by:
            //side by side: go to both ends, shorter end is our ship
            //perpendicular; go in both directions (perpendicular), our ship is a shorter length

            //easy case: ship is a sub, 1 square
            if (id == 1){
                //dont forget, we are inverting y and x for simplicity later
                theBoard[y][x] = 0;
            }

            //find direction
            else if (shipAt(x + 1, y) == id || shipAt(x - 1, y) == id ){ //if the ship to the right or left equals our ship
                //check how far right and how far left
                int howFarRight = 0;
                boolean changed = false;
                for (int i = 1; !changed; i++){
                    if (shipAt(x + i, y) == id) howFarRight++;
                    else changed = true;
                }

                changed = false;
                int howFarLeft = 0;
                for (int i = 1; !changed; i++){
                    if (shipAt(x-i, y) == id) howFarLeft++;
                    else changed = true;
                }

                //check for ship size > whats possible (meaning two ships side by side)
                int ourSize = howFarLeft + howFarRight + 1;
                if (ourSize == id){
                    //normal case, remove elements to the left and to the right
                    for (int i = 0; i < howFarRight; i++){
                        theBoard[y][x + i] = 0;
                    }
                    for (int i = 0; i < howFarLeft; i++){
                        theBoard[y][x - i] = 0;
                    }
                }else if (ourSize > id){
                    //we have two ships side by side
                    throw new AssertionError();
                    //TODO
                } else { //if oursize < id
                    //we made a catastrophic failure somewhere
                    //two vertical ships side by side? TODO
                    throw new AssertionError();
                }


            } else if (shipAt(x, y+ 1) == id || shipAt(x, y - 1) == id) {//if the ship above or below equals our ship
                int howFarUp = 0;
                boolean changed = false;
                for (int i = 0; !changed; i++){
                    if (shipAt(x + i, y) == id) howFarUp++;
                    else changed = true;
                }
                int howFarDown = 0;
                changed = false;
                for (int i = 0; !changed; i++){
                    if (shipAt(x-i, y) ==id) howFarDown++;
                    else changed = true;
                }

                int ourSize = howFarDown + howFarUp + 1;
                if (ourSize == id){
                    //normal case, remove elements above and below
                    for (int i = 0; i < howFarUp; i++){
                        theBoard[y + i][x] = 0;
                    }
                    for (int i = 0; i < howFarDown; i++){
                        theBoard[y-i][x] = 0;
                    }
                } else if (ourSize > id){ //one ship above another
                    //TODO
                    throw new AssertionError();
                } else if (ourSize < id){
                    // two vertical ships side by side, shouldn't have happened because we checked horizontals first
                    //two horizontals would be correctly interpreted first, two verticals would error out earlier
                    throw new AssertionError();
                }

            }
            return true;
        }
        else return false;
    }*/

    public boolean specificShipExistsAt(Ship ship, int x, int y){
        int startingX = ship.getStartingX();
        int startingY = ship.getStartingY();
        String orientation = ship.getOrientation();
        int size = ship.size();
        if (orientation.equals(Constants.HORIZONTAL) && y == startingY && startingX + size >= x && startingX > x){
            //if its vertical, y is the same, and startingX < x <= startingX+size, the point is contained
            return true;
        } else if (orientation.equals(Constants.VERTICAL) && x == startingX && startingY + size >= y && startingY > y){
            return true;
        }
        else return false;
    }
    public void removeFromBoard(Ship ship){
        int startingX = ship.getStartingX();
        int startingY = ship.getStartingY();
        int id = ship.id();
        String orientation = ship.getOrientation();
        int size = ship.size();

        for (int i = 0; i < ships.size() - 1; i++){
            if (ships.get(i).id() == id && ship.getStartingX() == startingX && ship.getStartingY() == startingY){
                ships.remove(i);
            }
        }

        if (orientation.equals(Constants.VERTICAL)){
            //our y axis is changing, x is staying the same
            for (int i = startingY; i < size + startingY; i++){ //TODO verify the -1
                theBoard[i][startingX] = 0;
            }
        }

        //TODO: theBoard isnt saving changes for some reason
        else if (orientation.equals(Constants.HORIZONTAL)){
            //x axis is changing, y is staying the same
            for (int i = 0; i < size + startingX; i++){
                int prev = theBoard[startingY][startingX + i]; //debug
                theBoard[startingY][startingX + i] = 0;
                int after = theBoard[startingY][startingX + i]; //debug
            }
        }
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}
