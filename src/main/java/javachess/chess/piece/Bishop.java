package javachess.chess.piece;

import javachess.chess.BoardContext;
import javachess.chess.ChessStrings;
import javachess.chess.Offset;

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
    public boolean canMoveImpl(Offset offset, BoardContext context) {
        for (Offset dir : Offset.DIAGONAL) {
            if (offset.isMultipleOf(dir)) {
                return isEmptyUntil(dir, offset, context);
            }
        }
        return false;
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
