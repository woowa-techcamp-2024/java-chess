package org.example.piece;

public class Pawn {

    private final String color; //todo: enum으로 변경

    public String getColor() {
        return this.color;
    }

    public Pawn() {
        this.color = "white";
    }

    public Pawn(String color) {
        this.color = color;
    }

}
