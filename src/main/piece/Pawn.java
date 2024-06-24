package piece;

public class Pawn {

    private final Color color;

    public Pawn() {
        this.color = Color.WHITE;
    }

    public Pawn(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
