package com.wootecam.chess.move;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.pieces.Pawn;
import com.wootecam.chess.pieces.Piece;
import java.util.Optional;

public class PieceMovementManager {
    private static final int INITIAL_BLACK_PAWN_ROW_INDEX = 1;
    private static final int INITIAL_WHITE_PAWN_ROW_INDEX = 6;

    private static boolean canPawnMoveFirst(Pawn pawn, Position from, Position to) {
        Direction directionFirstMove = pawn.getColor().isBlack() ? Direction.EE : Direction.WW;
        int rowFirstMove = pawn.getColor().isBlack() ? INITIAL_BLACK_PAWN_ROW_INDEX : INITIAL_WHITE_PAWN_ROW_INDEX;

        return from.x == rowFirstMove && from.moveBy(directionFirstMove).equals(to);
    }

    public void move(Board board, Position from, Position to) {
        if (board.isEmpty(from)) {
            throw new IllegalArgumentException("No piece found at the source position: " + from);
        }

        Piece piece = board.get(from);
        if (!(isPawnAndCanMove(board, piece, from, to) || isNotPawnAndCanMove(board, piece, from, to))) {
            throw new IllegalArgumentException("Cannot move " + from + " to " + to);
        }

        board.move(from, to);
    }

    private boolean isPawnAndCanMove(Board board, Piece piece, Position from, Position to) {
        return (piece.isPawn() && canPawnMove(board, (Pawn) piece, from, to));
    }

    private boolean canMoveExceptPawn(Board board, Piece piece, Position from, Position to) {
        if (isAllyPieceAtTarget(board, piece, to)) {
            return false;
        }

        Optional<Direction> correctDirection = piece.findCorrectDirection(from, to);
        if (correctDirection.isEmpty()) {
            return false;
        }
        Direction direction = correctDirection.get();

        if (piece.isKnight()) {
            return true;
        }

        Position nextPos = from.moveBy(direction);
        while (!nextPos.equals(to)) {
            if (!board.isEmpty(nextPos)) {
                return false;
            }
            nextPos = nextPos.moveBy(direction);
        }

        return true;
    }

    private boolean isNotPawnAndCanMove(Board board, Piece piece, Position from, Position to) {
        return !piece.isPawn() && canMoveExceptPawn(board, piece, from, to);
    }

    private boolean canPawnMove(Board board, Pawn pawn, Position from, Position to) {
        if (isAllyPieceAtTarget(board, pawn, to)) {
            return false;
        }
        boolean isTargetEmpty = board.isEmpty(to);

        Optional<Direction> correctDirection = pawn.findCorrectDirection(from, to);
        if (correctDirection.isEmpty()) {
            return canPawnMoveFirst(pawn, from, to) && isTargetEmpty;
        }
        Direction direction = correctDirection.get();

        Position nextPos = from.moveBy(direction);
        if (!nextPos.equals(to)) {
            return false;
        }

        if (direction.isDiagonal() && !isTargetEmpty) {
            return true;
        }

        return isTargetEmpty;
    }

    private boolean isAllyPieceAtTarget(Board board, Piece piece, Position target) {
        return board.isAllyPieceAt(target, piece);
    }
}
