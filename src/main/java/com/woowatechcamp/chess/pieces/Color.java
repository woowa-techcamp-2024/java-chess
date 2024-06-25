package com.woowatechcamp.chess.pieces;

import java.util.Arrays;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    NONE("none");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static Color fromString(String color) {
        if (color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        return Arrays.stream(Color.values())
                .filter(c -> c.color.equalsIgnoreCase(color))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such color: " + color));
    }
}
