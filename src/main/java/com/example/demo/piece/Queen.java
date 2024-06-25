package com.example.demo.piece;

public class Queen implements Piece {

    private final Color color;

    public Queen() {
        this(Color.WHITE);
    }

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public float getPoint() {
        return 9;
    }

    @Override
    public String toString(){
        return switch (color) {
            case WHITE -> "q";
            case BLACK -> "Q";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
