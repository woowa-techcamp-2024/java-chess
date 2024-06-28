package chess.pieces.type;

import java.util.Arrays;

public enum Representation {
    WHITE_PAWN("♙", Type.PAWN, Color.WHITE),
    BLACK_PAWN("♟", Type.PAWN, Color.BLACK),
    WHITE_KING("♔", Type.KING, Color.WHITE),
    BLACK_KING("♚", Type.KING, Color.BLACK),
    WHITE_QUEEN("♕", Type.QUEEN, Color.WHITE),
    BLACK_QUEEN("♛", Type.QUEEN, Color.BLACK),
    WHITE_BISHOP("♗", Type.BISHOP, Color.WHITE),
    BLACK_BISHOP("♝", Type.BISHOP, Color.BLACK),
    WHITE_KNIGHT("♘", Type.KNIGHT, Color.WHITE),
    BLACK_KNIGHT("♞", Type.KNIGHT, Color.BLACK),
    WHITE_ROOK("♖", Type.ROOK, Color.WHITE),
    BLACK_ROOK("♜", Type.ROOK, Color.BLACK),
    BLANK(".", Type.NO_PIECE, Color.NOCOLOR);

    private String symbol;
    private Type type;
    private Color color;

    private Representation(String symbol, Type type, Color color) {
        this.symbol = symbol;
        this.type = type;
        this.color = color;
    }

    public static Representation from(Type type, Color color) {
        return Arrays.stream(Representation.values())
                .filter(rep -> rep.getColor() == color)
                .filter(rep -> rep.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No representation for " + type + " " + color));
    }

    public String getSymbol() {
        return symbol;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }
}
