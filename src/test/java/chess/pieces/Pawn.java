package chess.pieces;

public class Pawn {

    private final Color color;

    public enum Color { WHITE, BLACK }

    public Pawn(Color color) { this.color = color; }

    public Pawn() { this.color = Color.WHITE; }

    public Color getColor() {
        return color;
    }

    public boolean verifyPawn(final Color color) {
        return color.equals(this.color);
    }
}
