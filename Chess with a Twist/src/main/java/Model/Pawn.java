package Model;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color, "Pawn");
    }

    /*the pawn is a basic chess-piece that moves in spaces of one forwards and diagonally
      when capturing.
    */
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int direction = color.equals("White") ? 1 : -1;

        if (startY == endY && board[endX][endY] == null) {
            return (endX - startX == direction);
        } else if (Math.abs(endY - startY) == 1 && endX - startX == direction && board[endX][endY] != null) {
            return !board[endX][endY].getColor().equals(color);
        }
        return false;
    }
}
//add functionality that allows pawn to move 2 spaces initially
//need to add code that permits a pawn to be sacrificed to bring back a fallen piece when reach the
//opposite side of the board.