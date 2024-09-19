package Model;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color, "Horse");
    }

    /*a horse moves in a unique manner, 2 spaces in one direction and then 1 space perpendicular.
      or the other way around, 1 space in one direction and 2 spaces perpendicular.
      just like other power pieces, horses can move forwards and backwards.
      A horse can 'jump' over any piece on the board.
    */

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int xMove = Math.abs(startX - endX);
        int yMove = Math.abs(startY - endY);

        if ((xMove == 2 && yMove == 1) || (xMove == 1 && yMove == 2)) {
            return board[endX][endY] == null || !board[endX][endY].getColor().equals(this.color);
        }
        return false;
    }
}