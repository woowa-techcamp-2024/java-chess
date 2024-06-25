package chess.pieces;

public class Piece {
    private final Color color;
    private final Type type;

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    // Knight
    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    // Bishop
    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    // Rook
    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    // Queen
    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    // King
    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }
    
    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }
    
    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return color.equals(Color.WHITE) ? type.getWhiteRepresentation() : type.getBlackRepresentation();
    }
    
    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
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
