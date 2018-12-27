import ships.*;

import java.util.ArrayList;
import java.util.Random;

public class RandomizedGame {
    public static void main(String[] args){
        Player player1 = new Player();
        Player player2 = new Player();

        Random random = new Random();

        ArrayList<Class> arrayList = new ArrayList<>();
        arrayList.add(AircraftCarrier.class);
        arrayList.add(Battleship.class);
        arrayList.add(Cruiser.class);
        arrayList.add(Destroyer.class);
        arrayList.add(Destroyer.class);
        arrayList.add(Submarine.class);
        arrayList.add(Submarine.class);

        //https://stackoverflow.com/questions/9335515/how-do-i-use-getconstructorparams-newinstanceargs
        for (Class c : arrayList){
            try{
                boolean success = false;
                while (!success){
                    if (player1.getOwnBoard().placeShip(
                            (Ship) c.getConstructor(int.class, int.class, String.class).newInstance(random.nextInt(10), random.nextInt(10), vOrHRandomizer(random)))){
                        success = true;
                    }
                }
                success = false;
                while (!success){
                    if (player2.getOwnBoard().placeShip(
                            (Ship) c.getConstructor(int.class, int.class, String.class).newInstance(random.nextInt(10), random.nextInt(10), vOrHRandomizer(random)))){
                        success = true;
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("Player 1's board: ");
        Game.print2D(player1.getOwnBoard().printBoard());
        System.out.println("Player 1 ships remaining: " + player1.getOwnBoard().getShips());

        System.out.println("Player 2's board: ");
        Game.print2D(player2.getOwnBoard().printBoard());
        System.out.println("Player 2 ships remaining: " + player2.getOwnBoard().getShips());

        while(!player1.getOwnBoard().getShips().isEmpty() && !player2.getOwnBoard().getShips().isEmpty()){
            int hitX = random.nextInt(10);
            int hitY = random.nextInt(10);
            System.out.println("Player 1 attacking player 2 at (" + hitX + ", " + hitY + ")");
            boolean hit = player1.attack(player2, hitX, hitY);
            if (hit){
                System.out.println("Ship hit!");
                System.out.println("Player 2's new board: ");
                Game.print2D(player2.getOwnBoard().printBoard());
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
                Game.print2D(player1.getOwnBoard().printBoard());
                System.out.println();
            } else System.out.println("Nothing was hit...");
            System.out.println("Player 1 ships remaining: " + player1.getOwnBoard().getShips());

        }

        if (player1.getOwnBoard().getShips().isEmpty()) System.out.println("Player 2 wins!");
        else if (player2.getOwnBoard().getShips().isEmpty()) System.out.println("Player 1 wins!");
    }

    public static String vOrHRandomizer(Random random){
        int nextInt = random.nextInt(2);
        switch (nextInt){
            case 0:
                return Constants.VERTICAL;
            case 1:
                return Constants.HORIZONTAL;

            default: return Constants.VERTICAL;
        }
    }
}
