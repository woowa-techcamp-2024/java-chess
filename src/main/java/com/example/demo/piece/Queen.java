package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public class Queen extends Piece {

    public Queen(Rank rank, File file) {
        super(rank, file);
    }

    public Queen(Color color, Rank rank, File file) {
        super(color, rank, file);
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
    public String toString(){
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
