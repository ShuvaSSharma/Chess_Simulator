package Model;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color, "Bishop");
    }

    /*A bishop moves diagonally through the board, it cannot move straight.
     */
    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int xMove = Math.abs(startX - endX);
        int yMove = Math.abs(startY - endY);

        if (xMove == yMove) {
            return isPathClear(startX, startY, endX, endY, board);
        }
        return false;
    }

    private boolean isPathClear(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int xDirection = Integer.compare(endX, startX);
        int yDirection = Integer.compare(endY, startY);

        int x = startX + xDirection;
        int y = startY + yDirection;
        while (x != endX || y != endY) {
            if (board[x][y] != null) {
                return false;
            }
            x += xDirection;
            y += yDirection;
        }
        return true;
    }
}
