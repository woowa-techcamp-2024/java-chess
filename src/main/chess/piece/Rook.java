package chess.piece;

import chess.ChessStrings;

public class Rook extends Piece {

    protected Rook() {
        super();
    }

    protected Rook(final Color color) {
        super(color);
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_ROOK;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_ROOK;
    }

}
