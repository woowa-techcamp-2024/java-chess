package com.seong.chess.pieces;

import com.seong.chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        ROOK('r', 5.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }
    }

    private final Type type;  // 추후 제거
    protected final Color color;
    protected final char representation;
    protected final double defaultPoint;

    public Piece(Type type, Color color, char representation, double defaultPoint) {
        this.type = type;
        this.color = color;
        this.representation = representation;
        this.defaultPoint = defaultPoint;
    }

    public char getRepresentation() {
        return isWhite() ? representation : Character.toUpperCase(representation);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public abstract boolean isNotBlank();

    public boolean isEqual(Type type, Color color) {
        return this.type == type && this.color == color;
    }

    public boolean isEqual(Color color) {
        return this.color == color;
    }

    public void checkMoveTargetPosition(String sourcePosition, String targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("이동 위치와 현재 위치가 동일합니다.");
        }
        List<Position> movablePosition = findMovablePosition(sourcePosition);
        if (movablePosition.contains(Position.convert(targetPosition))) {
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    public List<Position> findMovablePosition(String sourcePosition) {
        Position position = Position.convert(sourcePosition);
        List<Position> positions = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (!isPiecesDirection(direction)) {
                continue;
            }
            findNextPositions(position, direction, positions);
        }
        return positions;
    }

    protected abstract boolean isPiecesDirection(Direction direction);

    protected void findNextPositions(Position prevPosition, Direction direction, List<Position> positions) {
        if (Position.canNotMove(prevPosition.col() + direction.col, prevPosition.row() + direction.row)) {
            return;
        }
        Position nextPosition = new Position(prevPosition.col() + direction.col, prevPosition.row() + direction.row);
        positions.add(nextPosition);
        findNextPositions(nextPosition, direction, positions);
    }

    public Position nextPosition(String sourcePosition, Direction direction, int moveCount) {
        checkPieceCanMove(direction);
        Position position = Position.convert(sourcePosition);
        if (moveCount == 0) {
            return position;
        }
        Position nextPosition = new Position(position.col() + direction.col, position.row() + direction.row);
        return nextPosition(nextPosition.convert(), direction, moveCount - 1);
    }

    public abstract void checkPieceCanMove(Direction direction);

    public void checkSameColor(Piece targetPositionPiece) {
        if (color != targetPositionPiece.color) {
            return;
        }
        throw new IllegalArgumentException("현재 위치와 이동 위치의 기물이 같은 편입니다.");
    }

    public double getDefaultPoint() {
        return defaultPoint;
    }

    public String getColor() {
        return color.toString();
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return representation == piece.representation && Double.compare(defaultPoint, piece.defaultPoint) == 0
                && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation, defaultPoint);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", representation=" + representation +
                ", defaultPoint=" + defaultPoint +
                '}';
    }
}
