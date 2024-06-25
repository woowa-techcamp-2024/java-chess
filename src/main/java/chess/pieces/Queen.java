package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class Queen extends Piece {

    Queen(Color color) {
        super(color);
        this.symbol = color.equals(Color.BLACK) ? Symbol.BLACK_QUEEN : Symbol.WHITE_QUEEN;
        this.score = 9.0;
    }
}
