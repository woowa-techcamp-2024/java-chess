package chess;

import chess.board.Board;
import chess.board.Position;
import chess.exception.InvalidMoveException;
import chess.piece.Piece;
import chess.piece.PieceColor;
import chess.piece.Type;
import chess.piece.rule.Moveable;
import chess.piece.rule.PieceMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chess.exception.ExceptionConstant.INVALID_MOVE;
import static chess.exception.ExceptionConstant.INVALID_TURN;

//TODO: 프로모션(폰이 맨 끝에 도달하면 원하는 기물로 바꿔줌),
// 캐슬링(룩과 킹이 움직이지 않았을 때 킹이 두 칸 이동하고 룩이 킹 옆에 위치함), 앙파상(상대편 폰이 두 칸 이동해서 옆에 있는 폰을 먹을 수 있는 경우),
// 체크메이트(상대편 킹이 체크 상태이고 움직일 수 있는 곳이 없는 경우)
public class Rule {

    private final Board board;

    public Rule(final Board board) {
        this.board = board;
    }

    public boolean isMove(final Position source, final Position target, final PieceColor turn) {
        validateTurn(source, turn);
        validateTarget(source, target);

        return getValidMoveInBoard(source).stream()
                .anyMatch(position -> position.equals(target));
    }

    private void validateTurn(final Position source, final PieceColor turn) {
        if (!board.findPiece(source).getColor().equals(turn)) {
            throw new InvalidMoveException(INVALID_TURN);
        }
    }

    private void validateTarget(final Position source, final Position target) {
        if (board.findPiece(source).getColor().equals(board.findPiece(target).getColor())) {
            throw new InvalidMoveException(INVALID_MOVE);
        }
    }

    private List<Position> getValidMoveInBoard(final Position source) {
        Piece sourcePiece = board.findPiece(source);
        PieceMove moveable = sourcePiece.getMoveable();

        List<Position> validatePositions = new ArrayList<>();

        for (Moveable direction : moveable.directions()) {
            for (int distance = 1; distance <= moveable.distance(); distance++) {
                Optional<Position> targetPosition = getPosition(source, direction, distance);
                if (targetPosition.isEmpty()) break;

                Piece targetPiece = board.findPiece(targetPosition.get());

                if (isNonEmptyPiece(targetPiece)) {
                    if (!targetPiece.isSameColor(sourcePiece)) {
                        validatePositions.add(targetPosition.get());
                    }
                    break;
                }

                validatePositions.add(targetPosition.get());
            }
        }
        return validatePositions;
    }

    private static Optional<Position> getPosition(final Position source, final Moveable direction, final int distance) {
        try {
            return Optional.of(Position.of(source, direction, distance));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private boolean isNonEmptyPiece(final Piece piece) {
        return !piece.getType().equals(Type.NO_PIECE);
    }
}
