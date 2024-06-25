package pieces;

public class Piece {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    private final String color;
    private final PieceType pieceType;

    private Piece(String color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE_COLOR, PieceType.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK_COLOR, PieceType.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE_COLOR, PieceType.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK_COLOR, PieceType.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE_COLOR, PieceType.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK_COLOR, PieceType.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE_COLOR, PieceType.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK_COLOR, PieceType.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE_COLOR, PieceType.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK_COLOR, PieceType.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE_COLOR, PieceType.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK_COLOR, PieceType.KING);
    }

    public static Piece createBlank() {
        return new Piece(null, PieceType.BLANK);
    }

    public String getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public char getRepresentation() {
        return WHITE_COLOR.equals(color) ? Character.toLowerCase(pieceType.getRepresentation())
            : pieceType.getRepresentation();
    }

    public boolean isBlack() {
        return BLACK_COLOR.equals(color);
    }

    public boolean isWhite() {
        return WHITE_COLOR.equals(color);
    }

    public boolean isBlank() {
        return pieceType == PieceType.BLANK;
    }
}