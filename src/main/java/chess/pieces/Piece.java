package chess.pieces;

import java.util.Objects;

public class Piece {

    private final Color color;

    private final Type type;

    public enum Color {
        BLACK, WHITE, NO_COLOR;
    }

    public enum Type {
        PAWN('p', 1),
        KNIGHT('n', 2.5),
        ROOK('r', 5),
        BISHOP('b', 3),
        QUEEN('q', 9),
        KING('k', 0),
        NO_PIECE('.', 0);

        private char representation;

        private double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return this.equals(NO_PIECE) ? representation : Character.toUpperCase(representation);
        }
    }

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece createBlank() {
        return new Piece(Color.NO_COLOR, Type.NO_PIECE);
    }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
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

    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }

    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }

    public char getRepresentation() {
        return isBlack() ? type.getBlackRepresentation() : type.getWhiteRepresentation();
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public Type getType() {
        return type;
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isBlank() {
        return this.color.equals(Color.NO_COLOR) &&
                this.type.equals(Type.NO_PIECE);
    }

    public boolean hasColor(Color color) {
        return this.color.equals(color);
    }

    public boolean hasType(Type type) {
        return this.type.equals(type);
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
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    public double getDefaultPoint() {
        return this.type.defaultPoint;
    }
}
