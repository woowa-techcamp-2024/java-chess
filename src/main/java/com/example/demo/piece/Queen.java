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
    public String toString(){
        return switch (color) {
            case WHITE -> "q";
            case BLACK -> "Q";
        };
    }
}
