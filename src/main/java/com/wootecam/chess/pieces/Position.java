package com.wootecam.chess.pieces;

public record Position(int row, int column) {

    public int calculateDistanceWith(final Position targetPosition) {
        return Math.abs(row - targetPosition.row) + Math.abs(column - targetPosition.column);
    }

    public Position addPosition(final int rowVector, final int columnVector) {
        return new Position(row + rowVector, column + columnVector);
    }
}
