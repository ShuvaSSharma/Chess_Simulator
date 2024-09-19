package Service;

import Model.*;

import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    private ChessPiece[][] board;
    private int whiteKingX, whiteKingY;
    private int blackKingX, blackKingY;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard(List.of()); // Default to initialize with all pieces
    }

    public ChessBoard(List<String> piecesToRemove) {
        board = new ChessPiece[8][8];
        initializeBoard(piecesToRemove);
    }

    private void initializeBoard(List<String> piecesToRemove) {

        piecesToRemove.replaceAll(String::toLowerCase);

        // Initialize pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("White");
            board[6][i] = new Pawn("Black");
        }

        //Initialize horses
        if (!piecesToRemove.contains("Horse")){
            board[0][1] = new Horse("White");
            board[0][6] = new Horse("White");
            board[7][1] = new Horse("Black");
            board[7][6] = new Horse("Black");
        }

        // Initialize rooks
        if (!piecesToRemove.contains("Rook")) {
            board[0][0] = new Rook("White");
            board[0][7] = new Rook("White");
            board[7][0] = new Rook("Black");
            board[7][7] = new Rook("Black");
        }

        // Initialize bishops
        if (!piecesToRemove.contains("Bishop")) {
            board[0][2] = new Bishop("White");
            board[0][5] = new Bishop("White");
            board[7][2] = new Bishop("Black");
            board[7][5] = new Bishop("Black");
        }
        // Initialize queens
        if (!piecesToRemove.contains("Queen")) {
            board[0][3] = new Queen("White");
            board[7][3] = new Queen("Black");
        }

        // Initialize kings and store their positions
        board[0][4] = new King("White");
        board[7][4] = new King("Black");

        // Store kings' initial positions
        whiteKingX = 0; whiteKingY = 4;
        blackKingX = 7; blackKingY = 4;
    }

    // Add getters for the kings' positions
    public int[] getWhiteKingPosition() {
        return new int[] { whiteKingX, whiteKingY };
    }

    public int[] getBlackKingPosition() {
        return new int[] { blackKingX, blackKingY };
    }

    // Update the king's position if the king moves
    private void updateKingPosition(ChessPiece piece, int endX, int endY) {
        if (piece instanceof King) {
            if (piece.getColor().equals("White")) {
                whiteKingX = endX;
                whiteKingY = endY;
            } else {
                blackKingX = endX;
                blackKingY = endY;
            }
        }
    }

    // Check if a king is in danger (check)
    public boolean isKingInCheck(String kingColor) {
        int kingX, kingY;
        if (kingColor.equals("White")) {
            kingX = whiteKingX;
            kingY = whiteKingY;
        } else {
            kingX = blackKingX;
            kingY = blackKingY;
        }

        // Check if any opponent piece can attack the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece opponentPiece = board[i][j];
                if (opponentPiece != null && !opponentPiece.getColor().equals(kingColor)) {
                    if (opponentPiece.isValidMove(i, j, kingX, kingY, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Move piece and handle game end or check detection
    public boolean movePiece(int startX, int startY, int endX, int endY) {
        ChessPiece piece = board[startX][startY];
        if (piece != null && piece.isValidMove(startX, startY, endX, endY, board)) {
            ChessPiece targetPiece = board[endX][endY];

            // If a king is captured, end the game
            if (targetPiece instanceof King) {
                System.out.println("Game over! " + targetPiece.getColor() + " King has fallen.");
                System.exit(0);
            }

            // Move the piece
            board[endX][endY] = piece;
            board[startX][startY] = null;

            // Update king's position if a king moves
            updateKingPosition(piece, endX, endY);

            // Check if the opponent's king is in check
            if (isKingInCheck(piece.getColor().equals("White") ? "Black" : "White")) {
                System.out.println("Check!");
            }

            return true;
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getType().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
