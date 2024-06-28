package com.example.demo.piece;

public class Pawn extends Piece {

    public Pawn(PieceBuilder pieceBuilder) {
        super(pieceBuilder);
    }

    @Override
    public float getPoint() {
        return 0.5f;
    }

    @Override
    public Type getType() {
        return Type.PAWN;
    }

    @Override
    public String toString() {
        return switch (getColor()) {
            case WHITE -> "p";
            case BLACK -> "P";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
