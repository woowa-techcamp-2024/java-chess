package com.example.demo.piece;

import com.example.demo.context.File;
import com.example.demo.context.Location;
import com.example.demo.context.Rank;

public abstract class Piece implements Comparable<Piece> {

    private Color color;
    private final Rank rank;
    private final File file;
    private int turn;

    public static PieceBuilder builder(Type type) {
        return new PieceBuilder(type);
    }

    public static class PieceBuilder {
        private Color color = Color.WHITE;
        private Rank rank;
        private File file;
        private int turn = 0;
        private final Type type;

        private PieceBuilder(Type type) {
            this.type = type;
        }

        public PieceBuilder color(Color color) {
            this.color = color;
            return this;
        }

        public PieceBuilder rank(Rank rank) {
            this.rank = rank;
            return this;
        }

        public PieceBuilder file(File file) {
            this.file = file;
            return this;
        }

        public PieceBuilder turn(int turn) {
            this.turn = turn;
            return this;
        }

        public Piece build() {
            return switch (type) {
                case KING -> new King(this);
                case QUEEN -> new Queen(this);
                case ROOK -> new Rook(this);
                case BISHOP -> new Bishop(this);
                case KNIGHT -> new Knight(this);
                case PAWN -> new Pawn(this);
            };
        }
    }

    public Piece(PieceBuilder builder){
        this.color = builder.color;
        this.rank = builder.rank;
        this.file = builder.file;
    }

    public Color getColor(){
        return this.color;
    }

    public abstract float getPoint();

    public abstract Type getType();

    public boolean isLocatedAtInitLocation(Rank rank, File file){
        return this.rank == rank && this.file == file;
    }

    public Location getLocation(){
        return new Location(this.rank, this.file);
    }

    public File getFile() {
        return this.file;
    }

    public Rank getRank() {
        return this.rank;
    }
}
