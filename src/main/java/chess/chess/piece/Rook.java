package chess.chess.piece;

import chess.chess.BoardContext;
import chess.chess.ChessStrings;
import chess.chess.Offset;

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
    public boolean canMoveImpl(Offset offset, BoardContext context) {
        for (Offset dir : Offset.LINEAR) {
            if (offset.isMultipleOf(dir)) {
                return isEmptyUntil(dir, offset, context);
            }
        }
        return false;
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
