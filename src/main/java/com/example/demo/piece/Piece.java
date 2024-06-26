package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Rank;

public abstract class Piece implements Comparable<Piece> {

    private final Color color;
    private final Rank rank;
    private final File file;

    public Piece(Rank rank, File file) {
        this(Color.WHITE, rank, file);
    }

    public Piece(Color color, Rank rank, File file) {
        this.color = color;
        this.rank = rank;
        this.file = file;
    }

    public Color getColor(){
        return this.color;
    }

    public abstract float getPoint();

    public abstract Type getType();

    public boolean isLocatedAtInitLocation(Rank rank, File file){
        return this.rank == rank && this.file == file;
    }
}
