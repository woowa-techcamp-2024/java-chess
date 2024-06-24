package chess;

public class Pawn {
    String color;

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    public String getColor() {
        return color;
    }

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = WHITE_COLOR;
    }
}
