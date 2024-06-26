package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class Bishop extends Piece {

    Bishop(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_BISHOP : Symbol.WHITE_BISHOP, 3);
    }
}
