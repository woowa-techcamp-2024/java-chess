package chess.pieces;

import chess.pieces.enums.Symbol;

public class Blank extends Piece {

    public Blank() {
        super(null);
        this.symbol = Symbol.BLANK;
    }
}
