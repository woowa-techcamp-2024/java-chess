package com.example.demo.piece;

public class Queen extends Piece {

    public Queen(PieceBuilder builder) {
        super(builder);
    }

    @Override
    public float getPoint() {
        return 9;
    }

    @Override
    public Type getType() {
        return Type.QUEEN;
    }

    @Override
    public String toString() {
        return switch (getColor()) {
            case WHITE -> "q";
            case BLACK -> "Q";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
