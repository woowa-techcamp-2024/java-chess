package chess.piece;

import chess.ChessStrings;

public class King extends Piece {

    protected King() {
        super();
    }

    protected King(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return 0.0;
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
