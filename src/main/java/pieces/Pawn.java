package pieces;

public class Pawn {
    public static String WHITE_COLOR = "white";
    public static String BLACK_COLOR = "black";

    private String color;

    public Pawn() {
        this.color = "white";
    }

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
