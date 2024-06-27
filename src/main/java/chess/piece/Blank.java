package chess.piece;

import chess.exception.InvalidPieceException;
import chess.piece.rule.PieceMove;

import java.util.List;

import static chess.exception.ExceptionConstant.INVALID_PIECE;

public class Blank extends Piece {

    private Blank(final PieceColor color) {
        super(color);

        if (!color.equals(PieceColor.NO_COLOR)) {
            throw new IllegalArgumentException("빈 칸이 아닙니다.");
        }
    }

    public static Blank create() {
        return new Blank(PieceColor.NO_COLOR);
    }

    public static Blank create(final PieceColor color) {
        if (!color.equals(PieceColor.NO_COLOR)) {
            throw new InvalidPieceException(INVALID_PIECE);
        }
        return new Blank(color);
    }

    @Override
    public Type getType() {
        return Type.NO_PIECE;
    }

    @Override
    public PieceMove getMoveable() {
        return PieceMove.of(List.of(), 0);
    }
}
