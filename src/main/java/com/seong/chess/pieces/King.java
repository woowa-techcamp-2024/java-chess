package com.seong.chess.pieces;

import com.seong.chess.Position;
import java.util.List;

public class King extends Piece {

    private static final char REPRESENTATION = 'k';
    private static final double DEFAULT_POINT = 0.0;

    private King(Color color) {
        super(color, REPRESENTATION, DEFAULT_POINT);
    }

    public static King createWhite() {
        return new King(Color.WHITE);
    }

    public static King createBlack() {
        return new King(Color.BLACK);
    }

    @Override
    public boolean isNotBlank() {
        return false;
    }

    @Override
    protected boolean isPiecesDirection(Direction direction) {
        return direction.isDiagonal() || direction.isRight();
    }

    @Override
    protected void findNextPositions(Position prevPosition, Direction direction, List<Position> positions) {
        if (Position.canNotMove(prevPosition.col() + direction.col, prevPosition.row() + direction.row)) {
            return;
        }
        Position nextPosition = new Position(prevPosition.col() + direction.col, prevPosition.row() + direction.row);
        positions.add(nextPosition);
    }

    @Override
    public void checkPieceCanMove(Direction direction) {
        if (isPiecesDirection(direction)) {
            return;
        }
        throw new IllegalArgumentException("킹은 정방향, 대각선으로만 움직일 수 있습니다.");
    }
}
