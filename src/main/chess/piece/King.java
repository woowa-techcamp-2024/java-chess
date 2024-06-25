package chess.piece;

import chess.ChessStrings;

public class King extends Piece {

    public King() {
        super();
    }

    public King(final Color color) {
        super(color);
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_KING;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_KING;
    }

}
