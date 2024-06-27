package org.example.chess.pieces;

import java.util.List;
import java.util.Objects;
import org.example.chess.board.Position;
import org.example.chess.pieces.enums.Direction;
import org.example.utils.MathUtils;

public abstract class Piece {

    private final Color color;
    private final Type type;
    private final String representation;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.representation = initializeRepresentation();
    }

    protected abstract List<Direction> getDirections();
    protected double getMaxDistance() {
        return Double.MAX_VALUE;
    };

    public boolean isValidMove(String source, String destination,List<Position> positions) {
        Position from = new Position(source);
        Position to = new Position(destination);

        double distance = MathUtils.getDistance(from.getR(), from.getC(), to.getR(), to.getC());
        if (MathUtils.greaterThan(distance, getMaxDistance())) {
            return false;
        }
        Position delta = to.delta(from);
        List<Direction> directions = getDirections();
        for (Direction direction : directions) {
            if (direction.isMovableDirection(delta)) {
                return positions.isEmpty() || this.type == Type.KNIGHT;
            }
        }
        return false;
    }

    private String initializeRepresentation() {
        if (this.color == Color.BLACK) {
            return this.type.getRepresentation();
        }

        if (this.color == Color.WHITE) {
            return this.type.getRepresentation().toLowerCase();
        }

        return this.type.getRepresentation();
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public String getRepresentation() {
        return representation;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isPawn() {
        return this.type == Type.PAWN;
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
        return color == piece.color && type == piece.type && Objects.equals(representation, piece.representation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, representation);
    }

    public enum Color {

        WHITE("흰색"),
        BLACK("검은색"),
        NONCOLOR("무색");

        private final String description;

        Color(String description) {
            this.description = description;
        }
    }

    public enum Type {
        PAWN("P", 1.0),
        ROOK("R", 5.0),
        BISHOP("B", 3.0),
        KNIGHT("N", 2.5),
        QUEEN("Q", 9.0),
        KING("K", 0.0),
        NO_TYPE(".", 0.0);

        private final String representation;
        private final double defaultPoint;

        public String getRepresentation() {
            return this.representation;
        }

        public double getDefaultPoint() {
            return this.defaultPoint;
        }

        Type(String representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }
    }
}
