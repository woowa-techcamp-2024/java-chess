package chess.pieces.type;

public enum Type {
    PAWN(1.0),
    KING(0.0),
    QUEEN(9.0),
    BISHOP(3.0),
    KNIGHT(2.5),
    ROOK(5.0),
    NO_PIECE(0.0);

    private final double point;

    private Type(double point) {
        this.point = point;
    }

    public double getPoint() {
        return point;
    }
}
