package com.example.demo.piece;

public class King extends Piece {

    public King(PieceBuilder builder){
        super(builder);
    }

    @Override
    public float getPoint() {
        return 0;
    }

    @Override
    public Type getType() {
        return Type.KING;
    }

    @Override
    public String toString(){
        return switch (getColor()) {
            case WHITE -> "k";
            case BLACK -> "K";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
