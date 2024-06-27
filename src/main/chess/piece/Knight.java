package chess.piece;

import chess.ChessStrings;

public class Knight extends Piece {

    public static final double VALUE = 3.0;

    protected Knight(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return VALUE;
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_KNIGHT;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_KNIGHT;
    }

    public static Knight createBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight createWhite() {
        return new Knight(Color.WHITE);
    }

}
