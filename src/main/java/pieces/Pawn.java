package pieces;

public class Pawn {
    public final static String WHITE_COLOR = "white";
    public final static String BLACK_COLOR = "black";

    final private String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = WHITE_COLOR;
    }

    public String getColor() {
        return color;
    }
}
