package chess.pieces;

import java.util.Objects;

public record Piece (Color color, Type type) implements Comparable {

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    private static Piece createWhite(final Type type) { return new Piece(Color.WHITE, type); }
    private static Piece createBlack(final Type type) { return new Piece(Color.BLACK, type); }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() { return createBlack(Type.PAWN); }

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

    public static Piece createBlank() { return new Piece(Color.NO_COLOR, Type.NO_PIECE); }

    public boolean isWhite() { return Objects.equals(this.color, Color.WHITE); }

    public boolean isBlack() { return Objects.equals(this.color, Color.BLACK); }

    @Override
    public int compareTo(Object o) {
        Piece piece = (Piece) o;
        return Double.compare(piece.getType().getDefaultPoint(), this.getType().getDefaultPoint());
    }

    // test를 위한 메서드
    private static Type getTypeOfRepresentation(final char representation) {
        char lowerRepresentation = Character.toLowerCase(representation);
        switch (lowerRepresentation) {
            case 'p': return Type.PAWN;
            case 'r': return Type.ROOK;
            case 'n': return Type.KNIGHT;
            case 'b': return Type.BISHOP;
            case 'q': return Type.QUEEN;
            case 'k': return Type.KING;
        }
        return Type.NO_PIECE;
    }

    // test를 위한 메서드
    public static Piece create(final char representation) {
        if (representation == '.') return createBlank();
        if ('a' <= representation && representation <= 'z') return createWhite(getTypeOfRepresentation(representation));
        return createBlack(getTypeOfRepresentation(representation));
    }
}