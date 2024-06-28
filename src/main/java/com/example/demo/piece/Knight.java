package com.example.demo.piece;

public class Knight extends Piece {

    public Knight(PieceBuilder pieceBuilder) {
        super(pieceBuilder);
    }

    @Override
    public float getPoint() {
        return 2.5f;
    }

    @Override
    public Type getType() {
        return Type.KNIGHT;
    }

    @Override
    public String toString(){
        return switch (getColor()) {
            case WHITE -> "n";
            case BLACK -> "N";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
