package woowa.camp.pieces;

public class Piece {

    public static final String PAWN = "pawn";
    public static final String KNIGHT = "knight";
    public static final String BISHOP = "bishop";
    public static final String ROOK = "rook";
    public static final String QUEEN = "queen";
    public static final String KING = "king";

    private final String name;
    private final Color color;

    private Piece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public static Piece createPiece(final String name, final Color color) {
        return new Piece(name, color);
    }

    public static Piece createWhitePawn() {
        return new Piece(PAWN, Color.PAWN_WHITE);
    }

    public static Piece createBlackPawn() {
        return new Piece(PAWN, Color.PAWN_BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Piece(KNIGHT, Color.KNIGHT_WHITE);
    }

    public static Piece createBlackKnight() {
        return new Piece(KNIGHT, Color.KNIGHT_BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(BISHOP, Color.BISHOP_WHITE);
    }

    public static Piece createBlackBishop() {
        return new Piece(BISHOP, Color.BISHOP_BLACK);
    }

    public static Piece createWhiteRook() {
        return new Piece(ROOK, Color.ROOK_WHITE);
    }

    public static Piece createBlackRook() {
        return new Piece(ROOK, Color.ROOK_BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Piece(QUEEN, Color.QUEEN_WHITE);
    }

    public static Piece createBlackQueen() {
        return new Piece(QUEEN, Color.QUEEN_BLACK);
    }

    public static Piece createWhiteKing() {
        return new Piece(KING, Color.KING_WHITE);
    }

    public static Piece createBlackKing() {
        return new Piece(KING, Color.KING_BLACK);
    }

    public String getColor() {
        return color.getName();
    }

    public String getRepresentation() {
        return color.getRepresentation();
    }

    public String getName() {
        return name;
    }

    public boolean isPieceOf(final String name) {
        return this.name.equals(name);
    }

    public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isBlack() {
        return "black".equals(color.getName());
    }

    public boolean isWhite() {
        return "white".equals(color.getName());
    }


}
