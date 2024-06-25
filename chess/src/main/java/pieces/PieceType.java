package pieces;

public enum PieceType {
    PAWN("PAWN", 1.0),
    ROOK("ROOK", 5.0),
    KNIGHT("KNIGHT", 2.5),
    BISHOP("BISHOP", 3.0),
    QUEEN("QUEEN", 9.0),
    KING("KING", 0.0),
    NO_PIECE("NO_PIECE", 0.0),
    ;

    private final String type;
    private final double point;

    PieceType(String type, double point) {
        this.type = type;
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public double getPoint() {
        return point;
    }
}
