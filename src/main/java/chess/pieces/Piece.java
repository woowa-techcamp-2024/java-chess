package chess.pieces;

import java.util.Objects;

// VO
public class Piece {
    private final Representation representation;

    private Piece(Representation representation) {
        this.representation = representation;
    }

    public static Piece create(Representation.Type type, Color color) {
        return new Piece(Representation.from(type, color));
    }

    public String getName() {
        return representation.name();
    }

    public Color getColor() {
        return representation.getColor();
    }

    public String getSymbol() {
        return representation.getSymbol();
    }

    public Representation.Type getType() {
        return representation.getType();
    }

    public boolean isPieceOf(Representation representation) {
        return this.representation == representation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;

        return representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(representation);
    }

    @Override
    public String toString() {
        return "Piece " + hashCode() + " " +
                "representation=" + representation;
    }
}
