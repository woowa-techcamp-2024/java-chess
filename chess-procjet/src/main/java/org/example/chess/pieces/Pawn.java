package org.example.chess.pieces;

public class Pawn {

    private final Color color;

    public Color getColor() {
        return this.color;
    }

    public Pawn() {
        this.color = Color.WHITE;
    }

    public Pawn(Color color) {
        this.color = color;
    }

}
