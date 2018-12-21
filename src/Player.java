import ships.Ship;

import java.util.ArrayList;

public class Player {
    PlayerBoard ownBoard;
    OpponentBoard opponentsBoard;
    public Player(){
        ownBoard = new PlayerBoard();
        opponentsBoard = new OpponentBoard();
    }

    public PlayerBoard getOwnBoard() {
        return ownBoard;
    }

    public Board getOpponentsBoard() {
        return opponentsBoard;
    }

    public boolean attack(Player other, int x, int y){
        opponentsBoard.addPoint(x,y);
        if (!other.getOwnBoard().containsShipAt(x, y)){
            return false;
        }else{
            int id = other.getOwnBoard().shipAt(x, y);
            ArrayList<Ship> opponentsShips = other.getOwnBoard().getShips();
/*            for (Ship s : opponentsShips){
                if (other.getOwnBoard().specificShipExistsAt(s, x, y)){
                    other.getOwnBoard().removeFromBoard(s);
                    int[][] debugtable = other.getOwnBoard().printBoard();
                    return true;
                }
            }*/
            for (int i = 0; i < other.getOwnBoard().getShips().size() - 1; i++){
                if (other.getOwnBoard().specificShipExistsAt(opponentsShips.get(i), x, y)){
                    other.getOwnBoard().removeFromBoard(opponentsShips.get(i));
                }
            }
            return false;
        }
    }
}
