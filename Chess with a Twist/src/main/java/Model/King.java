package Model;

public class King extends ChessPiece {

    public King(String color) {
        super(color, "King");
    }

    //A king moves exactly one space in any direction.
    //the objective of the game is to capture the opposition's king while protecting your own.


    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int xMove = Math.abs(startX - endX);
        int yMove = Math.abs(startY - endY);

        if (xMove <= 1 && yMove <= 1) {
            return board[endX][endY] == null || !board[endX][endY].getColor().equals(this.color);
        }
        return false;
    }
}

//have a feature that includes the 'castling' function.