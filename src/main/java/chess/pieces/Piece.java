package chess.pieces;

// VO
public class Piece {
    private final Representations representation;

    private Piece(Representations representation) {
        this.representation = representation;
    }

    public String getName() {
        return representation.name();
    }

    public Colors getColor() {
        return representation.getColor();
    }

    public String getSymbol() {
        return representation.getSymbol();
    }

    public Representations.Type getType() {
        return representation.getType();
    }

    static public Piece create(Representations.Type type, Colors color) {
        return new Piece(Representations.from(type, color));
    }
}
