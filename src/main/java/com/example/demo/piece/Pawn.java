package com.example.demo.piece;

public class Pawn implements Piece {
    private Color color;

    public Pawn() {
        this(Color.WHITE);
    }

    public Pawn(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString(){
        return switch (color) {
            case WHITE -> "p";
            case BLACK -> "P";
        };
    }
}
