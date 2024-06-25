package chess.piece;

import chess.ChessStrings;

public class Bishop extends Piece {

    public Bishop() {
        super();
    }

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_BISHOP;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_BISHOP;
    }

}
