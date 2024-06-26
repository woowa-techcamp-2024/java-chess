package wootecamp.chess.pieces;

public class Piece {
    public enum Color {
        WHITE, BLACK, EMPTY
    }

    public enum Type {
        PAWN('p'),
        ROOK('r'),
        KNIGHT('n'),
        BISHOP('b'),
        QUEEN('q'),
        KING('k'),
        EMPTY('.');

        private final char representation;

        Type(final char representation) {
            this.representation = representation;
        }

        public char getRepresentation() {
            return representation;
        }
    }

    private final Type type;
    private final Color color;

    private Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public static final Piece EMPTY_PIECE = new Piece(Type.EMPTY, Color.EMPTY);

    public static Piece createWhitePawn() {
        return new Piece(Type.PAWN, Color.WHITE);
    }

    public static Piece createBlackPawn() {
        return new Piece(Type.PAWN, Color.BLACK);
    }

    public static Piece createWhiteRook() {
        return new Piece(Type.ROOK, Color.WHITE);
    }

    public static Piece createBlackRook() {
        return new Piece(Type.ROOK, Color.BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Type.KNIGHT, Color.WHITE);
    }

    public static Piece createBlackKnight() {
        return new Piece(Type.KNIGHT, Color.BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Type.BISHOP, Color.WHITE);
    }

    public static Piece createBlackBishop() {
        return new Piece(Type.BISHOP, Color.BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Type.QUEEN, Color.WHITE);
    }

    public static Piece createBlackQueen() {
        return new Piece(Type.QUEEN, Color.BLACK);
    }

    public static Piece createWhiteKing() {
        return new Piece(Type.KING, Color.WHITE);
    }

    public static Piece createBlackKing() {
        return new Piece(Type.KING, Color.BLACK);
    }

    public boolean isEmptyPiece() {
        return this.equals(EMPTY_PIECE);
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        char representation = type.getRepresentation();
        if(this.color == Color.BLACK) {
            return Character.toUpperCase(representation);
        }
        return representation;
    }
}
