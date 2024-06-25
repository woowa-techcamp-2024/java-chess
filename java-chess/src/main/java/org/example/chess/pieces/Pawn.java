package org.example.chess.pieces;

import org.example.chess.pieces.global.Position;

public class Pawn {
    private final Color color;
    private Position position;

    public Pawn() {
        this.color = Color.WHITE;
    }

    public Pawn(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public String getRepresentation() {
        return this.color.representation;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public enum Color {
        WHITE("p"), BLACK("P");

        public final String representation;

        Color(String representation) {
            this.representation = representation;
        }

        public String getRepresentation(){
            return this.representation;
        }
    }
}
