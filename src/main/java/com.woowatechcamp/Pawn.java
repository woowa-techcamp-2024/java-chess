package com.woowatechcamp;

public class Pawn {
    private final Color color;

    public Pawn() {
        this.color = Color.WHITE;
    }

    public Pawn(String color) {
        this.color = Color.fromString(color);
    }

    public String getColor() {
        return color.getColor();
    }
}
