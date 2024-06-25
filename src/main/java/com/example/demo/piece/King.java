package com.example.demo.piece;

public class King implements Piece {

    private final Color color;

    public King() {
        this(Color.WHITE);
    }

    public King(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
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
        return switch (color) {
            case WHITE -> "k";
            case BLACK -> "K";
        };
    }

    @Override
    public int compareTo(Piece piece) {
        return Float.compare(this.getPoint(), piece.getPoint());
    }
}
