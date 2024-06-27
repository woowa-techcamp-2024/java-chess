package com.wootecam.chess.move;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.Optional;

public class PieceMovementManager {

    public void move(Board board, Position from, Position to) {
        if (board.isEmpty(from)) {
            throw new IllegalArgumentException("No piece found at the source position: " + from);
        }

        Piece piece = board.get(from);
        if (!canMove(board, piece, from, to)) {
            throw new IllegalArgumentException("Cannot move " + from + " to " + to);
        }

        board.move(from, to);
    }

    private boolean canMove(Board board, Piece piece, Position from, Position to) {
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
            if (piece.isType(PieceType.KING)) {
                break;
            }
            nextPos = nextPos.moveBy(direction);
        }

        return true;
    }

    private boolean isAllyPieceAtTarget(Board board, Piece piece, Position target) {
        return board.isAllyPieceAt(target, piece);
    }
}
