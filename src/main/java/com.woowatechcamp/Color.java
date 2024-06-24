package com.woowatechcamp;

import java.util.Arrays;

public enum Color {
    BLACK("black"),
    WHITE("white");

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
