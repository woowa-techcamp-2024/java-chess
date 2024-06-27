package wootecamp.chess.pieces;

import java.util.Objects;

public class Piece {
    public enum Color {
        WHITE, BLACK, EMPTY
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        EMPTY('.', 0.0);

        private final char representation;
        private final double point;

        Type(final char representation, double point) {
            this.representation = representation;
            this.point = point;
        }

        public char getRepresentation() {
            return representation;
        }

        public double getPoint() {
            return point;
        }
    }

    private final Type type;
    private final Color color;

    private Piece(Color color, Type type) {
        this.type = type;
        this.color = color;
    }

    private static final Piece EMPTY_PIECE = new Piece(Color.EMPTY, Type.EMPTY);

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public static Piece getEmptyPiece() {
        return EMPTY_PIECE;
    }

    private static Piece createWhite(final Type type) {
        return new Piece(Color.WHITE, type);
    }

    private static Piece createBlack(final Type type) {
        return new Piece(Color.BLACK, type);
    }

    public boolean isSamePiece(Color color, Type type) {
        return this.color == color && this.type == type;
    }

    public boolean isEmptyPiece() {
        return this.equals(EMPTY_PIECE);
    }

    public boolean isKing(final Color color) {
        return this.type == Type.KING && this.color == color;
    }

    public boolean isPawn(Color color) {
        return this.type == Type.PAWN && this.color == color;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        char representation = type.getRepresentation();
        if(this.color == Color.BLACK) {
            return Character.toUpperCase(representation);
        }
        return representation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return type == piece.type && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
