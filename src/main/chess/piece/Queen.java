package chess.piece;

import chess.ChessStrings;

public class Queen extends Piece {

    public static final double VALUE = 9.0;

    protected Queen(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return VALUE;
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_QUEEN;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_QUEEN;
    }

    public static Queen createBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen createWhite() {
        return new Queen(Color.WHITE);
    }

}
