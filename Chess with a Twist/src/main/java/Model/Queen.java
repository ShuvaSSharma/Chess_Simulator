package Model;

public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color, "Queen");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int xMove = Math.abs(startX - endX);
        int yMove = Math.abs(startY - endY);

        // Queen moves like a Rook (straight) or Bishop (diagonally)
        if (xMove == yMove) {
            return isPathClear(startX, startY, endX, endY, board); // Diagonal move
        } else if (startX == endX || startY == endY) {
            return isPathClear(startX, startY, endX, endY, board); // Straight move
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