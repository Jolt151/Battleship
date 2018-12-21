import ships.*;

import java.util.Random;

public class Game {
    public static void main(String[] args){
        Player player1 = new Player();
        Player player2 = new Player();

        //add ships to player 1
        System.out.println(player1.getOwnBoard().placeShip(new AircraftCarrier(3 - 1,5 - 1, Constants.HORIZONTAL)));
        player1.getOwnBoard().placeShip(new Battleship(8 - 1, 5 - 1, Constants.VERTICAL));
        player1.getOwnBoard().placeShip(new Cruiser(6 - 1, 10 - 1, Constants.HORIZONTAL));
        player1.getOwnBoard().placeShip(new Destroyer(7 - 1, 1 - 1, Constants.HORIZONTAL));
        player1.getOwnBoard().placeShip(new Destroyer(2 - 1, 2 - 1, Constants.HORIZONTAL));
        player1.getOwnBoard().placeShip(new Submarine(3-1, 9 - 1, Constants.VERTICAL));
        player1.getOwnBoard().placeShip(new Submarine(9 - 1, 3 - 1, Constants.VERTICAL));
        System.out.println("Player 1's board: ");
        print2D(player1.getOwnBoard().printBoard());
        System.out.println("Player 1 ships remaining: " + player1.getOwnBoard().getShips());


        //add ships to player 2
        player2.getOwnBoard().placeShip(new AircraftCarrier(1-1, 5-1, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Battleship(4 - 1, 1-1, Constants.HORIZONTAL));
        player2.getOwnBoard().placeShip(new Cruiser(6-1, 7-1, Constants.HORIZONTAL));
        player2.getOwnBoard().placeShip(new Destroyer(10-1, 3 -1, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Destroyer(10-1, 5-1, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Submarine(1-1, 1-1, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Submarine(2-1, 2-1, Constants.VERTICAL));
        System.out.println("Player 2's board: ");
        print2D(player2.getOwnBoard().printBoard());
        System.out.println("Player 2 ships remaining: " + player2.getOwnBoard().getShips());


        //thetable doesnt contain our changes after we attack? TODO
        System.out.println("Attacking player 2 at 5,0");
        player1.attack(player2, 5, 0);
        System.out.println("Player 2's board: ");
        print2D(player2.getOwnBoard().printBoard());
        System.out.println("Player 2 ships remaining: " + player2.getOwnBoard().getShips());


        Random random = new Random();
        while(!player1.getOwnBoard().getShips().isEmpty() && !player2.getOwnBoard().getShips().isEmpty()){
            int hitX = random.nextInt(10);
            int hitY = random.nextInt(10);
            System.out.println("Player 1 attacking player 2 at (" + hitX + ", " + hitY + ")");
            boolean hit = player1.attack(player2, hitX, hitY);
            if (hit){
                System.out.println("Ship hit!");
                System.out.println("Player 2's new board: ");
                print2D(player2.getOwnBoard().printBoard());
                System.out.println();
            } else System.out.println("Nothing was hit...");
            System.out.println("Player 2 ships remaining: " + player2.getOwnBoard().getShips());


            hitX = random.nextInt(10);
            hitY = random.nextInt(10);
            System.out.println("Player 2 attacking player 1 at (" + hitX + ", " + hitY + ")");

            hit = player2.attack(player1, hitX, hitY);
            if (hit){
                System.out.println("Ship hit!");
                System.out.println("Player 1's new board: ");
                print2D(player1.getOwnBoard().printBoard());
                System.out.println();
            } else System.out.println("Nothing was hit...");
            System.out.println("Player 1 ships remaining: " + player1.getOwnBoard().getShips());

        }

    }
    public static void print2D(int[][] arr){
        for (int i = arr.length - 1; i >= 0; i--){
            for (int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    public void attack(Player other, int x, int y){

    }
}
