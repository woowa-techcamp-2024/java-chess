package com.example.demo.piece;

public class Rook implements Piece {

    private final Color color;

    public Rook() {
        this(Color.WHITE);
    }

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
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
        return switch (color) {
            case WHITE -> "r";
            case BLACK -> "R";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
