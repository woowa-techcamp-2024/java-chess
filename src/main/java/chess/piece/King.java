package chess.piece;

import chess.piece.rule.Direction;
import chess.piece.rule.Moveable;
import chess.piece.rule.PieceMove;

import java.util.List;
import java.util.stream.Stream;

public class King extends Piece {

    private static final int MAX_DISTANCE = 1;

    private King(final PieceColor color) {
        super(color);
    }

    public static King create(final PieceColor color) {
        return new King(color);
    }

    @Override
    public Type getType() {
        return Type.KING;
    }

    @Override
    public PieceMove getMoveable() {
        List<Moveable> kingDirection = Stream.concat(Direction.diagonalDirection().stream(),
                        Direction.linearDirection().stream())
                .toList();

        return PieceMove.of(kingDirection, MAX_DISTANCE);
    }
}
