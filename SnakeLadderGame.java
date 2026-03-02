import java.util.*;

public class SnakeLadderGame {

    // ===== ANSI COLORS =====
    static final String RESET = "\u001B[0m";

    static final String RED = "\u001B[31m";        // snake mouth
    static final String ORANGE = "\u001B[38;5;208m"; // snake tail
    static final String LIGHT_GREEN = "\u001B[92m"; // ladder bottom
    static final String DARK_GREEN = "\u001B[32m";  // ladder top
    static final String BLUE = "\u001B[34m";        // player 1
    static final String YELLOW = "\u001B[33m";      // player 2

    // ===== PRINT BOARD =====
    static void printBoard(int p1, int p2,
                           Map<Integer,Integer> snakes,
                           Map<Integer,Integer> ladders) {

        System.out.println("\n================ SNAKE & LADDER =================");

        for (int i = 100; i >= 1; i--) {

            String box;

            // players
            if (i == p1 && i == p2)
                box = BLUE + "B   " + RESET;
            else if (i == p1)
                box = BLUE + "P1  " + RESET;
            else if (i == p2)
                box = YELLOW + "P2  " + RESET;

            // snake mouth
            else if (snakes.containsKey(i))
                box = RED + String.format("%03dM", i) + RESET;

            // snake tail
            else if (snakes.containsValue(i))
                box = ORANGE + String.format("%03dT", i) + RESET;

            // ladder bottom
            else if (ladders.containsKey(i))
                box = LIGHT_GREEN + String.format("%03dB", i) + RESET;

            // ladder top
            else if (ladders.containsValue(i))
                box = DARK_GREEN + String.format("%03dT", i) + RESET;

            // normal box
            else
                box = String.format("%03d ", i);

            System.out.print("|" + box);

            if (i % 10 == 1)
                System.out.println("|");
        }

        System.out.println("=================================================");
        System.out.println(
                RED+"M"+RESET+" Snake Mouth  "
              + ORANGE+"T"+RESET+" Snake Tail  "
              + LIGHT_GREEN+"B"+RESET+" Ladder Bottom  "
              + DARK_GREEN+"T"+RESET+" Ladder Top\n");
    }

    // ===== MAIN GAME =====
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int p1 = 1, p2 = 1;

        // Snakes (mouth → tail)
        Map<Integer,Integer> snakes = new HashMap<>();
        snakes.put(99,54);
        snakes.put(70,55);
        snakes.put(52,42);
        snakes.put(25,2);

        // Ladders (bottom → top)
        Map<Integer,Integer> ladders = new HashMap<>();
        ladders.put(4,14);
        ladders.put(9,31);
        ladders.put(28,84);
        ladders.put(40,59);
        ladders.put(63,81);

        System.out.println("Welcome to Advanced Snake & Ladder!");
        System.out.println("Reach 100 to win.\n");

        while(true){

            printBoard(p1,p2,snakes,ladders);

            // PLAYER 1
            System.out.print("Player 1 press ENTER...");
            sc.nextLine();

            int dice = rand.nextInt(6)+1;
            System.out.println("Player 1 rolled: "+dice);

            if(p1 + dice <= 100) p1 += dice;

            if(snakes.containsKey(p1)){
                System.out.println(RED+"Snake Bite!"+RESET);
                p1 = snakes.get(p1);
            }
            else if(ladders.containsKey(p1)){
                System.out.println(LIGHT_GREEN+"Climb Ladder!"+RESET);
                p1 = ladders.get(p1);
            }

            if(p1 == 100){
                printBoard(p1,p2,snakes,ladders);
                System.out.println("Player 1 Wins!");
                break;
            }

            // PLAYER 2
            System.out.print("Player 2 press ENTER...");
            sc.nextLine();

            dice = rand.nextInt(6)+1;
            System.out.println("Player 2 rolled: "+dice);

            if(p2 + dice <= 100) p2 += dice;

            if(snakes.containsKey(p2)){
                System.out.println(RED+"Snake Bite!"+RESET);
                p2 = snakes.get(p2);
            }
            else if(ladders.containsKey(p2)){
                System.out.println(LIGHT_GREEN+"Climb Ladder!"+RESET);
                p2 = ladders.get(p2);
            }

            if(p2 == 100){
                printBoard(p1,p2,snakes,ladders);
                System.out.println("Player 2 Wins!");
                break;
            }
        }

        sc.close();
    }
}