package chess.piece;

import chess.ChessStrings;

public class Pawn extends Piece {

    protected Pawn() {
        super();
    }

    protected Pawn(final Color color) {
        super(color);
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_PAWN;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_PAWN;
    }

}
