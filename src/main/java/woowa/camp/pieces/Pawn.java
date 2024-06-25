package woowa.camp.pieces;

public class Pawn {

    private final Color color;

    public Pawn() {
        this.color = Color.PAWN_WHITE;
    }

    public Pawn(final Color color) {
        this.color = color;
    }

    public String getColor() {
        return color.getName();
    }

    public char getRepresentation() {
        return color.getRepresentation();
    }

}
