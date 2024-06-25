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
    public String toString(){
        return switch (color) {
            case WHITE -> "k";
            case BLACK -> "K";
        };
    }
}
