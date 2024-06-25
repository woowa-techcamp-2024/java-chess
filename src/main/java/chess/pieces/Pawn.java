package chess.pieces;

import chess.pieces.enums.Color;

public class Pawn implements Piece {

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    public boolean verifyPawn(final Color color) {
        return this.color.equals(color);
    }
}
