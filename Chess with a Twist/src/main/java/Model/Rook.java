package Model;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color, "Rook");
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        if (startX == endX || startY == endY) {
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