package chess.piece;

import chess.BoardContext;
import chess.ChessStrings;
import chess.Offset;

import java.util.List;

public class Pawn extends Piece {

    public static final double VALUE = 1.0;

    public static final double MULTIPLE_IN_FILE_VALUE = 0.5;

    protected Pawn(final Color color) {
        super(color);
    }

    @Override
    public double value() {
        return VALUE;
    }

    @Override
    public boolean canMoveImpl(Offset offset, BoardContext context) {
        Offset forward = getForward();
        if (offset.equals(forward)) {
            return true;
        }
        if (offset.equals(forward.multiply(2))) {
            return notMoved() && context.isEmptyAt(forward);
        }

        List<Offset> diagonals = getDiagonal();
        for (Offset diagonal : diagonals) {
            if (offset.equals(diagonal)) {
                Offset side = getSideFromDiagonal(diagonal);
                Color opposite = getColor().opposite();
                return context.isColorAt(diagonal, opposite) || context.isColorAt(side, opposite);
            }
        }

        return false;
    }

    private Offset getForward() {
        return isWhite() ? Offset.NORTH : Offset.SOUTH;
    }

    private List<Offset> getDiagonal() {
        return isWhite() ? List.of(Offset.NORTHEAST, Offset.NORTHWEST) : List.of(Offset.SOUTHEAST, Offset.SOUTHWEST);
    }

    private Offset getSideFromDiagonal(Offset diagonal) {
        if (diagonal.equals(Offset.NORTHEAST) || diagonal.equals(Offset.NORTHWEST)) {
            return Offset.EAST;
        } else if (diagonal.equals(Offset.SOUTHEAST) || diagonal.equals(Offset.SOUTHWEST)) {
            return Offset.WEST;
        } else {
            throw new IllegalArgumentException("Not diagonal: " + diagonal);
        }
    }

    @Override
    protected String whiteRepresentation() {
        return ChessStrings.WHITE_PAWN;
    }

    @Override
    protected String blackRepresentation() {
        return ChessStrings.BLACK_PAWN;
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK);
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE);
    }

}
