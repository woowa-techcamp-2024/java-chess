package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class King extends Piece {

    King(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_KING : Symbol.WHITE_KING, 0);

    }
}
