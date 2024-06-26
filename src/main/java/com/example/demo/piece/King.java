package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public class King extends Piece {

    public King(Rank rank, File file) {
        super(rank, file);
    }

    public King(Color color, Rank rank, File file) {
        super(color, rank, file);
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
