package org.example.piece;

public class Pawn {

    private final String color; //todo: enum으로 변경

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

}
