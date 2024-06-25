package com.example.demo.piece;

public class Pawn implements Piece {
    private final Color color;

    public Pawn() {
        this(Color.WHITE);
    }

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public float getPoint() {
        return 0.5f;
    }

    @Override
    public String toString(){
        return switch (color) {
            case WHITE -> "p";
            case BLACK -> "P";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
