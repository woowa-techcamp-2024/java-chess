package com.wootecam;

public class Pawn {
    private static final String DEFAULT_COLOR = "white";

    private final String color;

    public Pawn() {
        this.color = DEFAULT_COLOR;
    }

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
