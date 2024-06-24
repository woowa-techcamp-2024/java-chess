package com.wootecam;

public class Pawn {

    public static final String COLOR_WHITE = "white";
    public static final String COLOR_BLACK = "black";

    private final String color;

    public Pawn() {
        this(COLOR_WHITE);
    }

    public Pawn(final String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
