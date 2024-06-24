package chess.pieces;

public class Pawn {
    private final String color;

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    public Pawn() { this.color = "white"; }

    public Pawn(final String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}