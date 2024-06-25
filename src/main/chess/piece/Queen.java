package chess.piece;

import chess.ChessStrings;

public class Queen extends Piece {

    protected Queen() {
        super();
    }

    protected Queen(final Color color) {
        super(color);
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_QUEEN;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_QUEEN;
    }

}
