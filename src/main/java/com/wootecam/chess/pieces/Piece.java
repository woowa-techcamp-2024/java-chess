package com.wootecam.chess.pieces;

public class Piece {
    private static final Color DEFAULT_COLOR = Color.WHITE;

    private final PieceType type;
    private final Color color;
    private final PieceRepresentation representation;

    public Piece(PieceType pieceType) {
        this.type = pieceType;
        this.color = DEFAULT_COLOR;
        this.representation = PieceRepresentation.findByTypeAndColor(pieceType, DEFAULT_COLOR);
    }

    public Piece(PieceType pieceType, Color color) {
        this.type = pieceType;
        this.color = color;
        this.representation = PieceRepresentation.findByTypeAndColor(pieceType, color);
    }

    public Color getColor() {
        return color;
    }

    public PieceRepresentation getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return representation.value;
    }
}
