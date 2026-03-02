import java.util.*;

public class SnakeLadderGame {

    // ANSI COLORS
    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String BLUE = "\u001B[34m";
    static final String YELLOW = "\u001B[33m";

    // Print board with colors
    static void printBoard(int p1, int p2,
                           Set<Integer> ladders,
                           Set<Integer> snakes) {

        System.out.println("\n============= BOARD =============");

        for (int i = 30; i >= 1; i--) {

            String box;

            // BOTH players
            if (i == p1 && i == p2)
                box = BLUE + "B  " + RESET;

            // Player 1
            else if (i == p1)
                box = BLUE + "P1 " + RESET;

            // Player 2
            else if (i == p2)
                box = YELLOW + "P2 " + RESET;

            // Ladder (GREEN)
            else if (ladders.contains(i))
                box = GREEN + String.format("%02dL", i) + RESET;

            // Snake (RED)
            else if (snakes.contains(i))
                box = RED + String.format("%02dS", i) + RESET;

            // Normal box
            else
                box = String.format("%02d  ", i);

            System.out.print("|" + box);

            if (i % 10 == 1)
                System.out.println("|");
        }

        System.out.println("=================================");
        System.out.println(
            BLUE+"P1"+RESET+" Player1   "
          + YELLOW+"P2"+RESET+" Player2   "
          + GREEN+"L"+RESET+" Ladder   "
          + RED+"S"+RESET+" Snake\n");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int p1 = 1, p2 = 1;

        HashMap<Integer,Integer> board = new HashMap<>();

        // Ladders
        board.put(3,22);
        board.put(5,8);
        board.put(11,26);

        // Snakes
        board.put(27,1);
        board.put(21,9);
        board.put(17,4);

        Set<Integer> ladders = new HashSet<>(Arrays.asList(3,5,11));
        Set<Integer> snakes = new HashSet<>(Arrays.asList(27,21,17));

        System.out.println("Welcome to Colored Snake & Ladder!");
        System.out.println("Reach 30 to win.\n");

        while(true){

            printBoard(p1,p2,ladders,snakes);

            // PLAYER 1
            System.out.print("Player 1 press ENTER...");
            sc.nextLine();

            int dice = rand.nextInt(6)+1;
            System.out.println("Player 1 rolled: "+dice);

            if(p1+dice<=30) p1+=dice;

            if(board.containsKey(p1)){
                if(ladders.contains(p1))
                    System.out.println(GREEN+"Ladder! Move up!"+RESET);
                else
                    System.out.println(RED+"Snake! Move down!"+RESET);

                p1 = board.get(p1);
            }

            if(p1==30){
                printBoard(p1,p2,ladders,snakes);
                System.out.println("Player 1 Wins!");
                break;
            }

            // PLAYER 2
            System.out.print("Player 2 press ENTER...");
            sc.nextLine();

            dice = rand.nextInt(6)+1;
            System.out.println("Player 2 rolled: "+dice);

            if(p2+dice<=30) p2+=dice;

            if(board.containsKey(p2)){
                if(ladders.contains(p2))
                    System.out.println(GREEN+"Ladder! Move up!"+RESET);
                else
                    System.out.println(RED+"Snake! Move down!"+RESET);

                p2 = board.get(p2);
            }

            if(p2==30){
                printBoard(p1,p2,ladders,snakes);
                System.out.println("Player 2 Wins!");
                break;
            }
        }

        sc.close();
    }
}