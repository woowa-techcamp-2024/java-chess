package com.wootecam.chess.pieces;

public enum Color {
    WHITE("white"),
    BLACK("black");

    public final String displayName;

    Color(String displayName) {
        this.displayName = displayName;
    }

    public PieceRepresentation getRepresentation() {
        return switch (this) {
            case WHITE -> PieceRepresentation.WHITE_PAWN;
            case BLACK -> PieceRepresentation.BLACK_PAWN;
        };
    }
}
