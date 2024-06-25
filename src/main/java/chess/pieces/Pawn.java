package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class Pawn extends Piece {

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
        this.symbol = color.equals(Color.BLACK) ? Symbol.BLACK_PAWN : Symbol.WHITE_PAWN;
    }

    public boolean verifyPawn(final Color color) {
        return this.color.equals(color);
    }
}
