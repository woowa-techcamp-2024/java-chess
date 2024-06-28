package com.wootecam.chess.pieces;

public enum Type {
    PAWN("p", 1.0),
    ROOK("r", 5.0),
    KNIGHT("n", 2.5),
    BISHOP("b", 3.0),
    QUEEN("q", 9.0),
    KING("k", 0.0),
    NO_PIECE(".", 0.0);

    private final String representation;
    private final double point;

    Type(String representation, double point) {
        this.representation = representation;
        this.point = point;
    }

    public static double getPawnPoint(int pawnCount) {
        if (pawnCount == 1) {
            return PAWN.point;
        }
        return pawnCount * PAWN.point / 2.0;
    }

    public static boolean isOtherType(final Type type) {
        return type != NO_PIECE && type != PAWN;
    }

    public String findRepresentation(Color color) {
        if (color == Color.BLACK) {
            return representation.toUpperCase();
        }
        return representation;
    }

    public boolean isPiece() {
        return this != PAWN && this != NO_PIECE;
    }

    public double getPoint() {
        return point;
    }
}
