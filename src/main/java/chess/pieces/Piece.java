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

    public Representations getRepresentation() {
        return representation;
    }

    static public Piece createWhitePawn() {
        return new Piece(Representations.WHITE_PAWN);
    }

    static public Piece createBlackPawn() {
        return new Piece(Representations.BLACK_PAWN);
    }

    static public Piece createWhiteKing() {
        return new Piece(Representations.WHITE_KING);
    }

    static public Piece createBlackKing() {
        return new Piece(Representations.BLACK_KING);
    }

    static public Piece createWhiteRook() {
        return new Piece(Representations.WHITE_ROOK);
    }

    static public Piece createBlackRook() {
        return new Piece(Representations.BLACK_ROOK);
    }

    static public Piece createWhiteKnight() {
        return new Piece(Representations.WHITE_KNIGHT);
    }

    static public Piece createBlackKnight() {
        return new Piece(Representations.BLACK_KNIGHT);
    }

    static public Piece createWhiteBishop() {
        return new Piece(Representations.WHITE_BISHOP);
    }

    static public Piece createBlackBishop() {
        return new Piece(Representations.BLACK_BISHOP);
    }

    static public Piece createWhiteQueen() {
        return new Piece(Representations.WHITE_QUEEN);
    }

    static public Piece createBlackQueen() {
        return new Piece(Representations.BLACK_QUEEN);
    }
}
