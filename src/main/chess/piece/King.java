package chess.piece;

import chess.ChessStrings;

public class King extends Piece {

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

    public static King createBlack() {
        return new King(Color.BLACK);
    }

    public static King createWhite() {
        return new King(Color.WHITE);
    }

}
