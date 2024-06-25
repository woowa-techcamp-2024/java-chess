package woowa.camp.pieces;

public class Piece {

    private final Color color;

    public Piece() {
        this.color = Color.PAWN_WHITE;
    }

    public Piece(final Color color) {
        this.color = color;
    }

    public String getColor() {
        return color.getName();
    }

    public String getRepresentation() {
        return color.getRepresentation();
    }

}
