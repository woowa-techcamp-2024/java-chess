package com.example.demo.piece;

public class Pawn {
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
}
