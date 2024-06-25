package chess.pieces;

public class Pawn {
    private final Colors color;

    public Pawn(Colors color) {
        this.color = color;
    }

    public Pawn() {
        this.color = Colors.WHITE;
    }

    public Colors getColor() {
        return color;
    }

    public Representations getRepresentation() {
        return Representations.getPawn(color);
    }
}
