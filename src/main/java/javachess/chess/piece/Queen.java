package javachess.chess.piece;

import javachess.chess.BoardContext;
import javachess.chess.ChessStrings;
import javachess.chess.Offset;

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
    public boolean canMoveImpl(Offset offset, BoardContext context) {
        for (Offset dir : Offset.EVERY) {
            if (offset.isMultipleOf(dir)) {
                return isEmptyUntil(dir, offset, context);
            }
        }
        return false;
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
