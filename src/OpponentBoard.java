import ships.Ship;

import java.util.ArrayList;

public class OpponentBoard extends Board{
    public void addPoint(int x, int y){
        theBoard[y][x] = 1;
    }
    public void removePoint(int x, int y){
        theBoard[y][x] = 0;
    }
}
