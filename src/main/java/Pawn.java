public class Pawn {

    public static final String WHITE_COLOR = "white";

    public static final String BLACK_COLOR = "black";

    private static final String DEFAULT_COLOR = WHITE_COLOR;

    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = DEFAULT_COLOR;
    }

    public String getColor() {
        return this.color;
    }
}
