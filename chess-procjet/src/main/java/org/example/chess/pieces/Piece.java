package org.example.chess.pieces;

public class Piece {

    private final Color color;
    private final Type type;
    private final String representation;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.representation = initializeRepresentation();
    }

    private String initializeRepresentation() {
        if (this.color == Color.BLACK) {
            return this.type.getRepresentation();
        }

        if (this.color == Color.WHITE) {
            return this.type.getRepresentation().toLowerCase();
        }

        throw new IllegalArgumentException("잘못된 색상이 지정돼 있습니다.");
    }

    public static Piece createPiece(Color color, Type name) {
        return new Piece(color, name);
    }

    public Color getColor() {
        return color;
    }

    public Type getName() {
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
        PAWN("P"),
        KNIGHT("N"),
        ROOK("R"),
        BISHOP("B"),
        QUEEN("Q"),
        KING("K");

        private final String representation;

        public String getRepresentation() {
            return this.representation;
        }

        Type(String representation) {
            this.representation = representation;
        }
    }
}
