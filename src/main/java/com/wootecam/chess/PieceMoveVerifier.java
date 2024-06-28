package com.wootecam.chess;

import com.wootecam.chess.pieces.Direction;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Position;
import com.wootecam.chess.pieces.Type;
import java.util.function.Function;
import java.util.stream.IntStream;

public class PieceMoveVerifier {

    public static final int PAWN_FIRST_MOVE_MAX_AMOUNT = 2;

    public void verifyMove(final Piece startPiece,
                           final Piece targetPiece,
                           final Position startPosition,
                           final Position targetPosition,
                           final Function<Position, Piece> pieceFinder) {
        Direction direction = startPiece.findDirection(startPosition, targetPosition)
                .orElseThrow(() -> new IllegalArgumentException("현재 기물에 대한 알맞은 방향을 찾을 수 없습니다."));

        if (startPiece.isTypeOf(Type.PAWN)) {
            verifyPawnMove(startPosition, targetPosition, startPiece, targetPiece, direction, pieceFinder);
        }
        if (startPiece.isTypeOf(Type.KNIGHT)) {
            verifyKnight(startPiece, targetPiece);
        }
        verifyOtherMove(startPosition, targetPosition, startPiece, targetPiece, direction, pieceFinder);
    }

    private void verifyPawnMove(final Position startPosition,
                                final Position targetPosition,
                                final Piece startPiece,
                                final Piece targetPiece,
                                final Direction direction,
                                final Function<Position, Piece> pieceFinder) {
        validateSameTeam(startPiece, targetPiece);
        if (direction.isPawnAttackDirection() && targetPiece.isBlank()) {
            throw new IllegalArgumentException("폰은 빈칸을 공격할 수 없습니다.");
        }
        if (direction.isPawnMoveDirection()
                && startPosition.calculateDistanceWith(targetPosition) == PAWN_FIRST_MOVE_MAX_AMOUNT
                && isBlockWithOtherPiece(startPosition, direction, startPosition.calculateDistanceWith(targetPosition),
                pieceFinder)) {
            throw new IllegalArgumentException("폰이 2칸 이동할때 방문하는 칸에 기물이 있으면 안됩니다.");
        }
        if (direction.isPawnMoveDirection() && isBlockWithOtherPiece(startPosition, direction,
                startPosition.calculateDistanceWith(targetPosition), pieceFinder)) {
            throw new IllegalArgumentException("폰이 1칸 이동할때 도착지점에 기물이 있으면 안됩니다.");
        }
    }

    private void verifyKnight(final Piece startPiece, final Piece targetPiece) {
        validateSameTeam(startPiece, targetPiece);
    }

    private void verifyOtherMove(final Position startPosition,
                                 final Position targetPosition,
                                 final Piece startPiece,
                                 final Piece targetPiece,
                                 final Direction direction,
                                 final Function<Position, Piece> pieceFinder) {
        int moveCountExceptLastPiece = startPosition.calculateDistanceWith(targetPosition) - 1;

        validateSameTeam(startPiece, targetPiece);
        if (isBlockWithOtherPiece(startPosition, direction, moveCountExceptLastPiece, pieceFinder)) {
            throw new IllegalArgumentException("이동하는 중간에 다른 기물이 있으면 안됩니다.");
        }
    }

    private void validateSameTeam(final Piece startPiece, final Piece targetPiece) {
        if (isSameTeam(startPiece, targetPiece)) {
            throw new IllegalArgumentException("같은 팀의 기물이 있는곳으로 이동할 수 없습니다.");
        }
    }

    private boolean isSameTeam(final Piece startPiece, final Piece targetPiece) {
        return targetPiece.getColor() == startPiece.getColor();
    }

    private boolean isBlockWithOtherPiece(final Position startPosition,
                                          final Direction direction,
                                          final int moveCount,
                                          final Function<Position, Piece> pieceFinder) {
        return !IntStream.range(0, moveCount)
                .mapToObj(i -> startPosition.addPosition(direction.getRow(), direction.getColumn()))
                .map(pieceFinder)
                .allMatch(Piece::isBlank);
    }
}
