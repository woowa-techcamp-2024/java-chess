package chess.piece;

public abstract class Piece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    protected final String color;
    protected final char representation;

    protected Piece(String color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }

    public String getColor(){
        return color;
    }

    public boolean isWhite() {
        return color.equals(WHITE_COLOR);
    }

    public boolean isBlack() {
        return color.equals(BLACK_COLOR);
    }
}
