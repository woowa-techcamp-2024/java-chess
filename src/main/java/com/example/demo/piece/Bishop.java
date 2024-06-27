package com.example.demo.piece;

public class Bishop extends Piece {

    public Bishop(PieceBuilder builder) {
        super(builder);
    }

    @Override
    public float getPoint() {
        return 3;
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }

    @Override
    public String toString(){
        return switch (getColor()) {
            case WHITE -> "b";
            case BLACK -> "B";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
