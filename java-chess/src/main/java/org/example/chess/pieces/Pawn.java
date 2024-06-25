package org.example.chess.pieces;

public class Pawn {
    private Color color;

    public Pawn() {
        this.color = Color.WHITE;
    }

    public Pawn(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public enum Color {
        WHITE("white"), BLACK("BLACK");

        private final String color;

        Color(String color) {
            this.color = color;
        }

        public String getColor(){
            return this.color;
        }
    }
}
