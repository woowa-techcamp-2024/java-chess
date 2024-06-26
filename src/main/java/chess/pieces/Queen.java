package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class Queen extends Piece {

    Queen(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_QUEEN : Symbol.WHITE_QUEEN, 9.0);
    }
}
