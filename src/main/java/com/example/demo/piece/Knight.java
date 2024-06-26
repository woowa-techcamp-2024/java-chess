package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public class Knight extends Piece {

    public Knight(Rank rank, File file) {
        super(rank, file);
    }

    public Knight(Color color, Rank rank, File file) {
        super(color, rank, file);
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
