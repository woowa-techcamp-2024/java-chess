package com.seong.chess.pieces;

import com.seong.chess.Position;
import java.util.List;

public class Pawn extends Piece {

    private static final char REPRESENTATION = 'p';
    public static final double DEFAULT_POINT = 1.0;

    private Pawn(Color color) {
        super(color, REPRESENTATION, DEFAULT_POINT);
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE);
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    protected boolean isPiecesDirection(Direction direction) {
        if (color == Color.WHITE && direction == Direction.NORTH) {
            return true;
        }
        if (color == Color.BLACK && direction == Direction.SOUTH) {
            return true;
        }
        return false;
    }

    @Override
    protected void findNextPositions(Position prevPosition, Direction direction, List<Position> positions) {
        if (Position.canNotMove(prevPosition.col() + direction.col, prevPosition.row() + direction.row)) {
            return;
        }
        Position nextPosition = new Position(prevPosition.col() + direction.col, prevPosition.row() + direction.row);
        positions.add(nextPosition);
        if (prevPosition.isPawnRow()) {
            findNextPositions(nextPosition, direction, positions);
        }
    }

    @Override
    public Position nextPosition(String sourcePosition, Direction direction, int moveCount) {
        checkPieceCanMove(direction);
        Position position = Position.convert(sourcePosition);
        checkMoveCount(position, moveCount);
        return new Position(position.col() + direction.col * moveCount, position.row() + direction.row * moveCount);
    }

    @Override
    public void checkPieceCanMove(Direction direction) {
        if (isPiecesDirection(direction)) {
            return;
        }
        throw new IllegalArgumentException("폰은 전진만 할 수 있습니다.");
    }

    private void checkMoveCount(Position position, int moveCount) {
        if (moveCount > 2) {
            throw new IllegalArgumentException("폰은 2칸 이하로만 움직일 수 있습니다.");
        }
        if (moveCount == 2) {
            if (position.isPawnRow()) {
                return;
            }
            throw new IllegalArgumentException("초기상태의 폰만 2칸 이상으로 움직일 수 있습니다.");
        }

    }
}
