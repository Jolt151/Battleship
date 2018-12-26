import ships.*;

import java.util.Random;

public class Game {
    public static void main(String[] args){
        Player player1 = new Player();
        Player player2 = new Player();

        //add ships to player 1
        System.out.println(player1.getOwnBoard().placeShip(new AircraftCarrier(3,4, Constants.HORIZONTAL)));
        player1.getOwnBoard().placeShip(new Battleship(7, 4, Constants.VERTICAL));
        player1.getOwnBoard().placeShip(new Cruiser(5, 5, Constants.HORIZONTAL));
        player1.getOwnBoard().placeShip(new Destroyer(6, 0, Constants.HORIZONTAL));
        player1.getOwnBoard().placeShip(new Destroyer(1, 1, Constants.HORIZONTAL));
        player1.getOwnBoard().placeShip(new Submarine(2, 8, Constants.VERTICAL));
        player1.getOwnBoard().placeShip(new Submarine(8, 2, Constants.VERTICAL));
        System.out.println("Player 1's board: ");
        print2D(player1.getOwnBoard().printBoard());
        System.out.println("Player 1 ships remaining: " + player1.getOwnBoard().getShips());


        //add ships to player 2
        player2.getOwnBoard().placeShip(new AircraftCarrier(0, 4, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Battleship(3, 0, Constants.HORIZONTAL));
        player2.getOwnBoard().placeShip(new Cruiser(5, 6, Constants.HORIZONTAL));
        player2.getOwnBoard().placeShip(new Destroyer(9, 2, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Destroyer(9, 4, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Submarine(0, 0, Constants.VERTICAL));
        player2.getOwnBoard().placeShip(new Submarine(1, 1, Constants.VERTICAL));
        System.out.println("Player 2's board: ");
        print2D(player2.getOwnBoard().printBoard());
        System.out.println("Player 2 ships remaining: " + player2.getOwnBoard().getShips());


        System.out.println("Attacking player 2 at 0,4");
        player1.attack(player2, 0,4);
        System.out.println("Player 2's board: ");
        print2D(player2.getOwnBoard().printBoard());
        System.out.println("Player 2 ships remaining: " + player2.getOwnBoard().getShips());

        System.out.println("Attacking player 2 at 0,0");
        player1.attack(player2, 0,0);
        System.out.println("Player 2's board: ");
        print2D(player2.getOwnBoard().printBoard());
        System.out.println("Player 2 ships remaining: " + player2.getOwnBoard().getShips());

        System.out.println("Attacking player 2 at 1,1");
        player1.attack(player2, 1,1);
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

        if (player1.getOwnBoard().getShips().isEmpty()) System.out.println("Player 2 wins!");
        else if (player2.getOwnBoard().getShips().isEmpty()) System.out.println("Player 1 wins!");

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
