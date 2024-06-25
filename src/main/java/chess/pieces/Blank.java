package chess.pieces;

import chess.pieces.enums.Symbol;

public class Blank extends Piece {

    static final Blank INSTANCE = new Blank();

    private Blank() {
        super(null);
        this.symbol = Symbol.BLANK;
    }
}
