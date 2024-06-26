package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public class Bishop extends Piece {

    public Bishop(Rank rank, File file) {
        super(Color.WHITE, rank, file);
    }

    public Bishop(Color color, Rank rank, File file) {
        super(color, rank, file);
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
