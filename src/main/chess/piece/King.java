package chess.piece;

import chess.BoardContext;
import chess.ChessStrings;
import chess.Offset;

public class King extends Piece {

    protected King(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return 0.0;
    }

    @Override
    public boolean canMoveImpl(Offset offset, BoardContext context) {
        for (Offset dir : Offset.EVERY) {
            if (offset.equals(dir)) return true;
        }
        return false;
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
