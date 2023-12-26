// Jacob Fonger
// A02316298

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Assignment6Driver {

    public static void main(String[] args) {

        testGame();
        System.out.println();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");
    }

    private static void playGame(String filename) {
        ArrayList<Integer> moves = getMoves(filename);
        HexGame game = new HexGame(11);
        boolean blueWins;
        boolean redWins;
        for (int i = 0; i < moves.size(); i++) {
            if (i % 2 ==0){
                blueWins = game.playBlue(moves.get(i), false);
                if (blueWins) {
                    System.out.println("Blue wins at position " + moves.get(i) + "!!!!\n");
                    break;
                }
            }
            else {
                redWins = game.playRed(moves.get(i), false);
                if (redWins) {
                    System.out.println("Red wins at position " + moves.get(i) + "!!!!\n");
                    break;
                }
            }




        }
        printGrid(game);


    }

    private static ArrayList<Integer> getMoves(String filename){
        File file = new File(filename);
        ArrayList<Integer> moves = new ArrayList<>();

        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                String move = input.next();
                try {
                    moves.add(Integer.parseInt(move));
                } catch(NumberFormatException e){
                    System.out.println("\nError: " + filename + " contains values that are not integers \n\t" + e);
                }

            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
        return moves;
    }

    //
    // TODO: You can use this to compare with the output show in the assignment while working on your code
    private static void testGame() {
        HexGame game = new HexGame(11);

        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

//        printGrid(game);
    }

        // TODO: Complete this method
    private static void printGrid(HexGame game) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";
        String gameStr = "";
        for (int i = 0; i < (Math.pow(game.getSize(), 2)); i++){
            if (game.redMoves.contains(i)) {
                gameStr += ANSI_RED + "R" + ANSI_RESET;
            }
            else if (game.blueMoves.contains(i)){
                gameStr += ANSI_BLUE + "B" + ANSI_RESET;
            }
            else {
                gameStr += "X";
            }
            if ((i + 1) % game.getSize() == 0) { // if at right edge
                gameStr += "\n";
                int row = i / game.getSize();
                for (int s = 0; s < row; s++){
                    gameStr += " ";
                }
            }

                gameStr += " ";


        }
        System.out.println(gameStr);
    }

    }

