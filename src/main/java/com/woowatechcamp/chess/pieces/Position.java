package com.woowatechcamp.chess.pieces;

import java.util.Objects;

public class Position {
    private final int xPos;
    private final int yPos;

    public Position(String position) {
        validate(position);
        xPos = getXPosition(position);
        yPos = getYPosition(position);
    }

    public Position(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private static void validate(String position) {
        if (position == null || position.length() != 2) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (position.charAt(0) < 'a' || position.charAt(0) > 'h') {
            throw new IllegalArgumentException("Invalid XPosition: " + position);
        }
        if (position.charAt(1) < '1' || position.charAt(1) > '8') {
            throw new IllegalArgumentException("Invalid YPosition: " + position);
        }
    }

    private int getXPosition(String position) {
        return position.charAt(0) - 'a';
    }

    private int getYPosition(String position) {
        return 7 - (position.charAt(1) - '1');
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xPos == position.xPos && yPos == position.yPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos);
    }

    @Override
    public String toString() {
        return "Position{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                '}';
    }
}
