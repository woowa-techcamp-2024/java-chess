package chess.pieces;

import java.util.Objects;

public class Piece implements Comparable<Piece> {

    private final Color color;
    private final Type type;
    private final Position position;

    private Piece(final Color color, final Type type, final Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (Objects.equals(o, null)) return false;

        Piece piece = (Piece) o;
        if (!Objects.equals(this.color, piece.getColor())) return false;
        if (!Objects.equals(this.type, piece.getType())) return false;

        return true;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return this.type;
    }

    public Position getPosition() {
        return this.position;
    }

    private static Piece createWhite(final Type type, final Position position) { return new Piece(Color.WHITE, type, position); }
    private static Piece createBlack(final Type type, final Position position) { return new Piece(Color.BLACK, type, position); }

    public static Piece createMovedPiece(final Piece piece, final Position position) {
        return new Piece(piece.getColor(), piece.getType(), position);
    }

    public static Piece createWhitePawn(final Position position) {
        return createWhite(Type.PAWN, position);
    }

    public static Piece createBlackPawn(final Position position) { return createBlack(Type.PAWN, position); }

    public static Piece createWhiteKnight(final Position position) {
        return createWhite(Type.KNIGHT, position);
    }

    public static Piece createBlackKnight(final Position position) {
        return createBlack(Type.KNIGHT, position);
    }

    public static Piece createWhiteRook(final Position position) {
        return createWhite(Type.ROOK, position);
    }

    public static Piece createBlackRook(final Position position) {
        return createBlack(Type.ROOK, position);
    }

    public static Piece createWhiteBishop(final Position position) {
        return createWhite(Type.BISHOP, position);
    }

    public static Piece createBlackBishop(final Position position) {
        return createBlack(Type.BISHOP, position);
    }

    public static Piece createWhiteQueen(final Position position) {
        return createWhite(Type.QUEEN, position);
    }

    public static Piece createBlackQueen(final Position position) {
        return createBlack(Type.QUEEN, position);
    }

    public static Piece createWhiteKing(final Position position) {
        return createWhite(Type.KING, position);
    }

    public static Piece createBlackKing(final Position position) {
        return createBlack(Type.KING, position);
    }

    public static Piece createBlank(final Position position) { return new Piece(Color.NO_COLOR, Type.NO_PIECE, position); }

    public boolean isWhite() { return Objects.equals(this.color, Color.WHITE); }

    public boolean isBlack() { return Objects.equals(this.color, Color.BLACK); }

    @Override
    public int compareTo(Piece piece) {
        return Double.compare(piece.getType().getDefaultPoint(), this.getType().getDefaultPoint());
    }
}