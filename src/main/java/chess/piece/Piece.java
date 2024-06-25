package chess.piece;

public abstract class Piece {
    public enum Color {
        WHITE, BLACK
    }

    protected final Color color;
    protected final char representation;

    protected Piece(Color color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }

    public Color getColor(){
        return color;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public abstract double getDefaultPoint();
}
