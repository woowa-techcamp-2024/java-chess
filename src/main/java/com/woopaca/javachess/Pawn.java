package com.woopaca.javachess;

public class Pawn {

    private final String color;

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
