package chess.piece;

import chess.ChessStrings;

public class Knight extends Piece {

    public Knight() {
        super();
    }

    public Knight(final Color color) {
        super(color);
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_KNIGHT;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_KNIGHT;
    }

}
