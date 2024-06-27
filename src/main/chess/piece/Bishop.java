package chess.piece;

import chess.ChessStrings;

public class Bishop extends Piece {

    public static final double VALUE = 3.0;

    protected Bishop(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return VALUE;
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_BISHOP;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_BISHOP;
    }

    public static Bishop createBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop createWhite() {
        return new Bishop(Color.WHITE);
    }

}
