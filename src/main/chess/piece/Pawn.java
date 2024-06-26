package chess.piece;

import chess.ChessStrings;

public class Pawn extends Piece {

    public static final double VALUE = 1.0;

    public static final double MULTIPLE_IN_FILE_VALUE = 0.5;

    protected Pawn() {
        super();
    }

    protected Pawn(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return VALUE;
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
