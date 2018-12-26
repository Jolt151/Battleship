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
            ArrayList<Ship> opponentsShips = other.getOwnBoard().getShips();
            for (int i = 0; i < opponentsShips.size(); i++){
                if (other.getOwnBoard().specificShipExistsAt(opponentsShips.get(i), x, y)){
                    other.getOwnBoard().removeFromBoard(opponentsShips.get(i));
                    return true;
                }
            }
            return false;
        }
    }
}
