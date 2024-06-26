package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public class Pawn extends Piece {

    public Pawn(Rank rank, File file) {
        super(rank, file);
    }

    public Pawn(Color color, Rank rank, File file) {
        super(color, rank, file);
    }

    @Override
    public float getPoint() {
        return 0.5f;
    }

    @Override
    public Type getType() {
        return Type.PAWN;
    }

    @Override
    public String toString() {
        return switch (getColor()) {
            case WHITE -> "p";
            case BLACK -> "P";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
