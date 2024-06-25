package chess.pieces;

public class Piece {
    private final String color;
    private final char representation;

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';
    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char BLACK_KING_REPRESENTATION = 'K';
    public static final char WHITE_KING_REPRESENTATION = 'k';


    public static Piece createWhitePawn() {
        return new Piece(WHITE_COLOR, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK_COLOR, BLACK_PAWN_REPRESENTATION);
    }

    // Knight
    public static Piece createWhiteKnight() {
        return new Piece(WHITE_COLOR, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK_COLOR, BLACK_KNIGHT_REPRESENTATION);
    }

    // Bishop
    public static Piece createWhiteBishop() {
        return new Piece(WHITE_COLOR, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK_COLOR, BLACK_BISHOP_REPRESENTATION);
    }

    // Rook
    public static Piece createWhiteRook() {
        return new Piece(WHITE_COLOR, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK_COLOR, BLACK_ROOK_REPRESENTATION);
    }

    // Queen
    public static Piece createWhiteQueen() {
        return new Piece(WHITE_COLOR, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK_COLOR, BLACK_QUEEN_REPRESENTATION);
    }

    // King
    public static Piece createWhiteKing() {
        return new Piece(WHITE_COLOR, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK_COLOR, BLACK_KING_REPRESENTATION);
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }


    private Piece(String color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public boolean isBlack() {
        return this.color.equals(BLACK_COLOR);
    }

    public boolean isWhite() {
        return this.color.equals(WHITE_COLOR);
    }

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p'),
        KNIGHT('n'),
        BISHOP('b'),
        ROOK('r'),
        QUEEN('q'),
        KING('k'),
        NO_PIECE('.');

        private final char representation;

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        Type(char representation) {
            this.representation = representation;
        }
    }
}
