package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public class Blank extends Piece {

    static final Blank INSTANCE = new Blank();

    private Blank() {
        super(Color.NONE, Symbol.BLANK, 0);
    }
}
