package chess.pieces;

public enum Representations {
    WHITE_PAWN("♙", Colors.WHITE),
    BLACK_PAWN("♟", Colors.BLACK),
    WHITE_KING("♔", Colors.WHITE),
    BLACK_KING("♚", Colors.BLACK),
    WHITE_QUEEN("♕", Colors.WHITE),
    BLACK_QUEEN("♛", Colors.BLACK),
    WHITE_BISHOP("♗", Colors.WHITE),
    BLACK_BISHOP("♝", Colors.BLACK),
    WHITE_KNIGHT("♘", Colors.WHITE),
    BLACK_KNIGHT("♞", Colors.BLACK),
    WHITE_ROOK("♖", Colors.WHITE),
    BLACK_ROOK("♜", Colors.BLACK);

    private String symbol;
    private Colors color;

    private Representations(String symbol, Colors color) {
        this.symbol = symbol;
        this.color = color;
    }

    public static Representations getPawn(Colors color) {
        if (color == Colors.WHITE) {
            return WHITE_PAWN;
        } else {
            return BLACK_PAWN;
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public Colors getColor() {
        return color;
    }
}
