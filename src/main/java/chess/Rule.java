package chess;

import chess.board.Board;
import chess.board.Position;
import chess.exception.InvalidMoveException;
import chess.piece.Piece;
import chess.piece.PieceColor;
import chess.piece.rule.PieceMove;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static chess.exception.ExceptionConstant.INVALID_TURN;

public class Rule {

    private final Board board;

    public Rule(final Board board) {
        this.board = board;
    }

    public boolean isMove(final Position source, final Position target, final PieceColor turn) {
        validateTurn(source, turn);

        return getValidMoveInBoard(source).stream()
                .anyMatch(position -> position.equals(target));
    }

    private void validateTurn(final Position source, final PieceColor turn) {
        if (!board.findPiece(source).getColor().equals(turn)) {
            throw new InvalidMoveException(INVALID_TURN);
        }
    }

    private List<Position> getValidMoveInBoard(final Position source) {
        Piece piece = board.findPiece(source);
        PieceMove moveable = piece.getMoveable();

        return moveable.directions().stream()
                .flatMap(direction ->
                        IntStream.rangeClosed(1, moveable.distance())
                                .mapToObj(distance -> {
                                    try {
                                        return Optional.of(Position.of(source, direction, distance));
                                    } catch (IllegalArgumentException e) {
                                        return Optional.<Position>empty();
                                    }
                                })
                )
                .flatMap(Optional::stream)
                .toList();
    }
}
