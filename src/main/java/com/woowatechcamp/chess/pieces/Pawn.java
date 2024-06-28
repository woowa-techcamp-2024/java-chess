package com.woowatechcamp.chess.pieces;

import com.woowatechcamp.chess.game.Board;

import java.util.List;

public class Pawn extends Piece {
    private int moveCount;

    public Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
        this.moveCount = 0;
    }

    @Override
    public void move(Position position, Board board) {
        validateMove(position, board);
        super.move(position, board);
        moveCount++;
    }

    @Override
    protected void validateMove(Position newPosition, Board board) {
        Direction direction = Direction.fromPositions(getPosition(), newPosition);
        int deltaY = newPosition.getYPos() - getPosition().getYPos();
        int deltaX = Math.abs(newPosition.getXPos() - getPosition().getXPos());

        // 전진 움직임 검증
        if (deltaX == 0) {
            validateForwardMove(newPosition, board, direction);
        }
        // 대각선 움직임 검증 (공격)
        else if (deltaX == 1 && Math.abs(deltaY) == 1) {
            validateDiagonalMove(newPosition, board, direction);
        } else {
            throw new IllegalArgumentException("Invalid pawn move");
        }
    }

    private void validateForwardMove(Position newPosition, Board board, Direction direction) {
        if (isMovingBackward(direction)) {
            throw new IllegalArgumentException("Pawn cannot move backwards");
        }

        List<Direction> validDirections = getColor() == Color.WHITE ?
                Direction.whitePawnForwardDirection() : Direction.blackPawnForwardDirection();

        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Invalid pawn move");
        }

        if (board.isOccupied(newPosition)) {
            throw new IllegalArgumentException("Cannot move to occupied position");
        }

        if (direction == Direction.NORTH_TWO || direction == Direction.SOUTH_TWO) {
            if (!isFirstMove()) {
                throw new IllegalArgumentException("Can only move two squares on first move");
            }
            Position intermediatePosition = new Position(getPosition().getXPos(), (getPosition().getYPos() + newPosition.getYPos()) / 2);
            if (board.isOccupied(intermediatePosition)) {
                throw new IllegalArgumentException("Cannot jump over pieces");
            }
        }
    }

    private void validateDiagonalMove(Position newPosition, Board board, Direction direction) {
        List<Direction> validDirections = getColor() == Color.WHITE ?
                Direction.whitePawnCaptureDirection() : Direction.blackPawnCaptureDirection();

        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Invalid direction for pawn capture");
        }

        if (!board.isOccupied(newPosition) || board.isOccupiedBySameColor(newPosition, getColor())) {
            throw new IllegalArgumentException("Pawn can only move diagonally when capturing");
        }
    }

    private boolean isMovingBackward(Direction direction) {
        return (getColor() == Color.BLACK && direction == Direction.SOUTH) ||
                (getColor() == Color.WHITE && direction == Direction.NORTH);
    }

    @Override
    public void undoMove(Position position) {
        moveCount--;
        super.undoMove(position);
    }

    private boolean isFirstMove() {
        return moveCount == 0;
    }
}
