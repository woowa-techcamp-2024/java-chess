package com.example.demo.piece;

public class Rook implements Piece {

    private final Color color;

    public Rook() {
        this(Color.WHITE);
    }

    public Rook(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString(){
        return switch (color) {
            case WHITE -> "r";
            case BLACK -> "R";
        };
    }
}
