import ships.Ship;

public class Board {
    protected int size;
    protected int[][] theBoard;
    public Board(){
        this.size = Constants.BOARD_SIZE;
        theBoard = new int[size][size];
        //fill up the matrix so we dont have to deal with nulls
        for (int i = 0; i < theBoard.length; i++){
            for(int j = 0; j < theBoard[0].length; j++){
                theBoard[i][j] = 0;
            }
        }
    }

    public int[][] printBoard(){
        return theBoard;
    }
}
