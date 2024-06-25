package chess.pieces;

public enum Representations {
    WHITE_PAWN("♙", Type.PAWN, Colors.WHITE),
    BLACK_PAWN("♟", Type.PAWN, Colors.BLACK),
    WHITE_KING("♔", Type.KING, Colors.WHITE),
    BLACK_KING("♚", Type.KING, Colors.BLACK),
    WHITE_QUEEN("♕", Type.QUEEN, Colors.WHITE),
    BLACK_QUEEN("♛", Type.QUEEN, Colors.BLACK),
    WHITE_BISHOP("♗", Type.BISHOP, Colors.WHITE),
    BLACK_BISHOP("♝", Type.BISHOP, Colors.BLACK),
    WHITE_KNIGHT("♘", Type.KNIGHT, Colors.WHITE),
    BLACK_KNIGHT("♞", Type.KNIGHT, Colors.BLACK),
    WHITE_ROOK("♖", Type.ROOK, Colors.WHITE),
    BLACK_ROOK("♜", Type.ROOK, Colors.BLACK),
    BLANK("", Type.NO_PIECE, Colors.NOCOLOR);

    private String symbol;
    private Type type;
    private Colors color;

    private Representations(String symbol, Type type, Colors color) {
        this.symbol = symbol;
        this.type = type;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public Colors getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        PAWN,
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        NO_PIECE
    }
}
