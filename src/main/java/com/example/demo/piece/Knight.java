package com.example.demo.piece;

public class Knight implements Piece {

    private final Color color;

    public Knight() {
        this(Color.WHITE);
    }

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public float getPoint() {
        return 2.5f;
    }

    @Override
    public String toString(){
        return switch (color) {
            case WHITE -> "n";
            case BLACK -> "N";
        };
    }
}
