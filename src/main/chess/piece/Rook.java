package chess.piece;

import chess.ChessStrings;

public class Rook extends Piece {

    public static final double VALUE = 5.0;

    protected Rook(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return VALUE;
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_ROOK;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_ROOK;
    }

    public static Rook createBlack() {
        return new Rook(Color.BLACK);
    }

    public static Rook createWhite() {
        return new Rook(Color.WHITE);
    }

}
