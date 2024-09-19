package Model;

public abstract class ChessPiece {
    protected String color;
    protected String type;

    public ChessPiece(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board);
}