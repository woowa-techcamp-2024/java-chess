package org.example.chess.pieces;

public class Piece {

    private final Color color;
    private final PieceName name;

    private Piece(Color color, PieceName name) {
        this.color = color;
        this.name = name;
    }

    public static Piece createPiece(Color color, PieceName name) {
        return new Piece(color, name);
    }
}
