package com.example.demo.piece;

public class Rook extends Piece {

    public Rook(PieceBuilder builder) {
        super(builder);
    }

    @Override
    public float getPoint() {
        return 5;
    }

    @Override
    public Type getType() {
        return Type.ROOK;
    }

    @Override
    public String toString(){
        return switch (getColor()) {
            case WHITE -> "r";
            case BLACK -> "R";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
