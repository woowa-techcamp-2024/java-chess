package chess;

import chess.board.Board;
import chess.board.Position;
import chess.exception.InvalidMoveException;
import chess.exception.InvalidPieceException;
import chess.piece.Piece;
import chess.piece.PieceColor;
import chess.piece.Type;
import chess.piece.rule.Moveable;
import chess.piece.rule.PieceMove;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static chess.exception.ExceptionConstant.*;

public class Rule {

    private final Board board;

    public Rule(final Board board) {
        this.board = board;
    }

    public void move(final Position source, final Position target, final PieceColor turn) {
        validateTurn(source, turn);
        validateTarget(source, target);

        if (getValidMoveInBoard(source).stream()
                .anyMatch(position -> position.equals(target))) {
            throw new InvalidMoveException(INVALID_MOVE);
        }

        board.move(source, target);
    }

    public boolean isPromotion(final Position target) {
        Piece piece = board.findPiece(target);

        if (!piece.getType().equals(Type.PAWN)) {
            return false;
        }

        PieceColor color = piece.getColor();
        int rankIndex = target.rank().getIndex();

        if (color.equals(PieceColor.WHITE) && rankIndex == 0) {
            return true;
        } else if (color.equals(PieceColor.BLACK) && rankIndex == 7) {
            return true;
        }

        return false;
    }

    public void promotion(final Position target, final String piece) {
        Type type = Type.of(piece);

        if (!enablePromotion(type)) {
            throw new InvalidPieceException(INVALID_PIECE);
        }

        Piece promotion = type.createPiece(board.findPiece(target).getColor());

        board.move(target, promotion);
    }

    //TODO: 캐슬링(룩과 킹이 움직이지 않았을 때 킹이 두 칸 이동하고 룩이 킹 옆에 위치함)
    public void castling(final Position source, final Position target, final PieceColor turn) {

    }

    //TODO: 앙파상(상대편 폰이 두 칸 이동해서 옆에 있는 폰을 먹을 수 있는 경우)
    public void anPassant(final Position source, final Position target, final PieceColor turn) {

    }

    public boolean isCheckmate(final PieceColor turn) {
        Position kingPosition = board.findKingPosition(turn.flip());

        List<Position> checkmatePositions = getEnablePositionList(kingPosition, board.findPiece(kingPosition));
        checkmatePositions.add(kingPosition);

        List<Position> positions = board.findPosition(turn);

        List<Position> enablePositions = positions.stream()
                .map(position -> getEnablePositionList(position, board.findPiece(position)))
                .flatMap(List::stream)
                .toList();

        boolean attackKing = new HashSet<>(enablePositions).containsAll(checkmatePositions);

        if (attackKing) {
            checkmatePositions.remove(kingPosition);
            return !findProtectKingPiece(turn.flip(), checkmatePositions, enablePositions);
        }

        return false;
    }

    private boolean findProtectKingPiece(final PieceColor turn, final List<Position> checkmatePositions, final List<Position> enablePositions) {
        return board.findPosition(turn).stream()
                .map(position -> getEnablePositionList(position, board.findPiece(position)))
                .flatMap(List::stream)
                .anyMatch(checkmatePositions::contains);
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

        return getEnablePositionList(source, sourcePiece);
    }

    private List<Position> getEnablePositionList(final Position position, final Piece sourcePiece) {
        PieceMove moveable = sourcePiece.getMoveable();

        List<Position> validatePositions = new ArrayList<>();

        for (Moveable direction : moveable.directions()) {
            for (int distance = 1; distance <= moveable.distance(); distance++) {
                Optional<Position> targetPosition = getPosition(position, direction, distance);
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

    private boolean enablePromotion(final Type type) {
        return (type.equals(Type.QUEEN) || type.equals(Type.BISHOP) || type.equals(Type.KNIGHT) || type.equals(Type.ROOK));
    }

}
