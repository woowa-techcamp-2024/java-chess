package chess;

import exception.InvalidMoveException;
import exception.OutOfBoardException;

import java.util.Scanner;

public class Main {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ChessGame chessGame = new ChessGame();

        while (true) {
            String str = scanner.next();
            if (str.equals(START)) {
                chessGame.initialize();
            } else if (str.equals(END)) {
                return;
            } else if (str.startsWith(MOVE)) {
                try {
                    chessGame.move(scanner.next(), scanner.next());
                } catch (InvalidMoveException | OutOfBoardException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(chessGame.showBoard());
            if(chessGame.isWhiteWin() || chessGame.isBlackWin()){
                fireFlowerBoom(chessGame.isWhiteWin());
                break;
            }
        }
    }

    private static void Winner(boolean isWhiteWin){
        // ANSI escape codes for colors
        System.out.println();
        String blackBackground = "\033[40m"; // Black background
        String whiteText = "\033[37m";       // White text
        String reset = "\033[0m";            // Reset color

        // Colored
        String winner = blackBackground + whiteText + "BLACK" + reset;
        if (isWhiteWin){
            winner = blackBackground + whiteText + "WHITE" + reset;
        }

        // Different colors for "WIN"
        String redText = "\033[31m";         // Red text
        String greenText = "\033[32m";       // Green text
        String blueText = "\033[34m";        // Blue text

        // Combined colored text
        String coloredWin = "W" + greenText + "I" + blueText + "N" + reset;

        // Print the combined text
        System.out.println("\t" + winner + " " + coloredWin);
    }

    private static void fireFlowerBoom(boolean isWhiteWin) throws InterruptedException {
        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Winner(isWhiteWin);


        // Define the firework pattern
        String[] firework = {
                "         \033[31m*\033[0m         ",
                "        \033[33m* *\033[0m        ",
                "       \033[32m* * *\033[0m       ",
                "      \033[34m* * * *\033[0m      ",
                "     \033[35m* * * * *\033[0m     ",
                "      \033[34m* * * *\033[0m      ",
                "       \033[32m* * *\033[0m       ",
                "        \033[33m* *\033[0m        ",
                "         \033[31m*\033[0m         "
        };

        // Display the firework
        for (String line : firework) {
            System.out.println(line);
            Thread.sleep(200);  // Wait to create an animation effect
        }

        // Clear the screen at the end
        Thread.sleep(1000);  // Pause to show the firework
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
