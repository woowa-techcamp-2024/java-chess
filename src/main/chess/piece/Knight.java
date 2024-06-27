package chess.piece;

import chess.BoardContext;
import chess.ChessStrings;
import chess.Offset;

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
    public boolean canMoveImpl(Offset offset, BoardContext context) {
        for (Offset dir : Offset.KNIGHT) {
            if (offset.equals(dir)) return true;
        }
        return false;
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
