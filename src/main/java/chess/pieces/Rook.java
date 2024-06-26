package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class Rook extends Piece {

    Rook(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_ROOK : Symbol.WHITE_ROOK, 5.0);
    }
}
