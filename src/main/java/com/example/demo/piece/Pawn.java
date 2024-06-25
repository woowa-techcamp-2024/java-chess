package com.example.demo.piece;

public class Pawn {
    private String color;

    public Pawn() {
        this("white");
    }

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
