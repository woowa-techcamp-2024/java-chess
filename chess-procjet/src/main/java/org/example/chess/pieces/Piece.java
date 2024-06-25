package org.example.chess.pieces;

public class Piece {

    private final Color color;
    private final PieceName name;
    private final String representation;

    private Piece(Color color, PieceName name) {
        this.color = color;
        this.name = name;
        this.representation = initializeRepresentation();
    }

    private String initializeRepresentation() {
        if (this.color == Color.BLACK) {
            return this.name.getRepresentation();
        }

        if (this.color == Color.WHITE) {
            return this.name.getRepresentation().toLowerCase();
        }

        throw new IllegalArgumentException("잘못된 색상이 지정돼 있습니다.");
    }

    public static Piece createPiece(Color color, PieceName name) {
        return new Piece(color, name);
    }

    public Color getColor() {
        return color;
    }

    public PieceName getName() {
        return name;
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
}
