package chess.pieces;

public class Piece {
    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char WHITE_KING_REPRESENTATION = 'k';
    public static final char BLACK_KING_REPRESENTATION = 'K';


    private final Color color;

    private final Name name;

    private final char representation;

    public enum Color {
        BLACK, WHITE;
    }

    public enum Name {
        PAWN, KNIGHT, ROOK, BISHOP, QUEEN, KING;
    }

    private Piece(Color color, Name name, char representation) {
        this.color = color;
        this.name = name;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Name.PAWN, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Name.PAWN, BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Name.KNIGHT, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Name.KNIGHT, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, Name.ROOK, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, Name.ROOK, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Name.BISHOP, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Name.BISHOP, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Name.QUEEN, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Name.QUEEN, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Name.KING, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Name.KING, BLACK_KING_REPRESENTATION);
    }


    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

}
