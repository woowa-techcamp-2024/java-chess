package org.example.chess.pieces;

public abstract class Piece {

    private final Color color;
    private final Type type;
    private final String representation;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.representation = initializeRepresentation();
    }

    public abstract boolean isValidMove(String source, String destination);

    private String initializeRepresentation() {
        if (this.color == Color.BLACK) {
            return this.type.getRepresentation();
        }

        if (this.color == Color.WHITE) {
            return this.type.getRepresentation().toLowerCase();
        }

        return this.type.getRepresentation();
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public String getRepresentation() {
        return representation;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isPawn() {
        return this.type == Type.PAWN;
    }

    public enum Color {

        WHITE("흰색"),
        BLACK("검은색"),
        NONCOLOR("무색");

        private final String description;

        Color(String description) {
            this.description = description;
        }
    }

    public enum Type {
        PAWN("P", 1.0),
        ROOK("R", 5.0),
        BISHOP("B", 3.0),
        KNIGHT("N", 2.5),
        QUEEN("Q", 9.0),
        KING("K", 0.0),
        NO_TYPE(".", 0.0);

        private final String representation;
        private final double defaultPoint;

        public String getRepresentation() {
            return this.representation;
        }

        public double getDefaultPoint() {
            return this.defaultPoint;
        }

        Type(String representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }
    }
}
