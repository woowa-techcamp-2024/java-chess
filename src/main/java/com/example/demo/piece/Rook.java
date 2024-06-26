package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public class Rook extends Piece {

    public Rook(Rank rank, File file) {
        super(rank, file);
    }

    public Rook(Color color, Rank rank, File file) {
        super(color, rank, file);
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
