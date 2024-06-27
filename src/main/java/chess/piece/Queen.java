package chess.piece;

import chess.piece.rule.Direction;
import chess.piece.rule.Moveable;
import chess.piece.rule.PieceMove;

import java.util.List;
import java.util.stream.Stream;

public class Queen extends Piece {

    private static final int MAX_DISTANCE = 8;

    private Queen(final PieceColor color) {
        super(color);
    }

    public static Queen create(final PieceColor color) {
        return new Queen(color);
    }

    @Override
    public Type getType() {
        return Type.QUEEN;
    }

    @Override
    public PieceMove getMoveable() {
        List<Moveable> queenDirections = Stream.concat(Direction.diagonalDirection().stream(),
                        Direction.linearDirection().stream())
                .toList();

        return PieceMove.of(queenDirections, MAX_DISTANCE);
    }
}
