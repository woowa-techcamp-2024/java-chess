package org.example.chess.pieces;

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

    public String getRepresentation() {
        return this.color.representation;
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

    public class Position {
        private int row;
        private int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
