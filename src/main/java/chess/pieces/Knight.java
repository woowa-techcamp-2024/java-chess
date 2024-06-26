package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class Knight extends Piece {

    Knight(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_KNIGHT : Symbol.WHITE_KNIGHT, 2.5);
    }
}
