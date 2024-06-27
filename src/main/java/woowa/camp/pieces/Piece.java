package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.NONE;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.NO_PIECE;

import java.util.Objects;

public class Piece {

    private final Type type;
    private final Color color;

    private Piece(final Type type, final Color color) {
        this.type = type;
        this.color = color;
    }

    public static Piece createPiece(final Type type, final Color color) {
        return new Piece(type, color);
    }

    public static Piece createWhitePieceOf(final Type type) {
        return new Piece(type, WHITE);
    }

    public static Piece createBlackPieceOf(final Type type) {
        return new Piece(type, BLACK);
    }

    public static Piece createBlank() {
        return new Piece(NO_PIECE, NONE);
    }

    public Representation getRepresentation() {
        return Representation.findMatchedRepresentation(type, color);
    }

    public Type getType() {
        return type;
    }

    public boolean isPieceOf(final Type type) {
        return this.type == type;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public enum Type {
        PAWN("pawn"),
        ROOK("rook"),
        KNIGHT("knight"),
        BISHOP("bishop"),
        QUEEN("queen"),
        KING("king"),
        NO_PIECE("noPiece");

        private final String name;

        Type(String name) {
            this.name = name;
        }
    }

    public enum Color {
        BLACK("black"),
        WHITE("white"),
        NONE("none");

        private final String name;

        Color(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return type == piece.type && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }

}
