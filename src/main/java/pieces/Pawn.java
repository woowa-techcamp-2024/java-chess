package pieces;

public class Pawn {

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    public Pawn() {
        this.color = Color.WHITE;
    }

    public Color getColor() {
        return color;
    }
}