package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println();
        System.out.println("Welcome to the best game in the world! C H E S S!");
        System.out.println();
        System.out.println("================================================");
        System.out.println("  Move pieces using their positions");
        System.out.println("  Enter your move in the format:");
        System.out.println("  Row Start, Column Start, Row End, Column End");
        System.out.println("================================================");
        System.out.println();

        // Ask the user if they want to play without specific pieces
        System.out.println("Before we begin,");
        System.out.println("would you like to remove any specific pieces? (e.g., Horse, Bishop, Queen)");
        System.out.println("***just in case you wanted to make things a bit more interesting***");
        System.out.println("Type the name of the piece(s) to remove, separated by commas, or press Enter to skip:");

        String userInput = scanner.nextLine();
        List<String> piecesToRemove = new ArrayList<>();

        // Parse user input
        if (!userInput.trim().isEmpty()) {
            String[] pieces = userInput.split(",");
            for (String piece : pieces) {
                piecesToRemove.add(piece.trim());
            }
        }

        // Create a new ChessBoard with the user's preferences
        ChessBoard chessBoard = new ChessBoard(piecesToRemove);

        // Game loop
        while (true) {
            chessBoard.printBoard();
            System.out.println("Enter your move (e.g., 0 1 2 1 for moving a piece from row 0, column 1 to row 2, column 1):");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();

            if (chessBoard.movePiece(startX, startY, endX, endY)) {
                System.out.println("Move successful!");
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    //test all features within the console
}

