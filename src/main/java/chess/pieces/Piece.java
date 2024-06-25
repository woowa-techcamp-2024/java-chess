package chess.pieces;

// VO
public class Piece {
    private final String name;  // pawn, knight, rook, bishop, queen, king
    private final Colors color;
    private final Representations representation;

    private Piece(String name, Colors color, Representations representation) {
        this.name = name;
        this.color = color;
        this.representation = representation;
    }

    public String getName() {
        return name;
    }

    public Colors getColor() {
        return color;
    }

    public Representations getRepresentation() {
        return representation;
    }

    static public Piece createWhitePawn() {
        return new Piece("pawn", Colors.WHITE, Representations.WHITE_PAWN);
    }

    static public Piece createBlackPawn() {
        return new Piece("pawn", Colors.BLACK, Representations.BLACK_PAWN);
    }

    static public Piece createWhiteKing() {
        return new Piece("king", Colors.WHITE, Representations.WHITE_KING);
    }

    static public Piece createBlackKing() {
        return new Piece("king", Colors.BLACK, Representations.BLACK_KING);
    }

    static public Piece createWhiteRook() {
        return new Piece("rook", Colors.WHITE, Representations.WHITE_ROOK);
    }

    static public Piece createBlackRook() {
        return new Piece("rook", Colors.BLACK, Representations.BLACK_ROOK);
    }

    static public Piece createWhiteKnight() {
        return new Piece("knight", Colors.WHITE, Representations.WHITE_KNIGHT);
    }

    static public Piece createBlackKnight() {
        return new Piece("knight", Colors.BLACK, Representations.BLACK_KNIGHT);
    }

    static public Piece createWhiteBishop() {
        return new Piece("bishop", Colors.WHITE, Representations.WHITE_BISHOP);
    }

    static public Piece createBlackBishop() {
        return new Piece("bishop", Colors.BLACK, Representations.BLACK_BISHOP);
    }

    static public Piece createWhiteQueen() {
        return new Piece("queen", Colors.WHITE, Representations.WHITE_QUEEN);
    }

    static public Piece createBlackQueen() {
        return new Piece("queen", Colors.BLACK, Representations.BLACK_QUEEN);
    }
}
