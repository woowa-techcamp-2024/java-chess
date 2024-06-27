package chess.pieces;

import chess.pieces.Position.Degree;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final Color color;

    private final Type type;

    private final List<Direction> directionList;

    protected Piece(Color color, Type type, List<Direction> directionList) {
        this.color = color;
        this.type = type;
        this.directionList = directionList;
    }

    public enum Color {
        BLACK, WHITE, NO_COLOR;
    }

    public enum Type {
        PAWN('p', 1),
        KNIGHT('n', 2.5),
        ROOK('r', 5),
        BISHOP('b', 3),
        QUEEN('q', 9),
        KING('k', 0),
        NO_PIECE('.', 0);

        private char representation;

        private double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return this.equals(NO_PIECE) ? representation : Character.toUpperCase(representation);
        }
    }

    public char getRepresentation() {
        return isBlack() ? type.getBlackRepresentation() : type.getWhiteRepresentation();
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public Type getType() {
        return type;
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isBlank() {
        return this.color.equals(Color.NO_COLOR) &&
                this.type.equals(Type.NO_PIECE);
    }

    public boolean hasColor(Color color) {
        return this.color.equals(color);
    }

    public boolean hasType(Type type) {
        return this.type.equals(type);
    }

    public double getDefaultPoint() {
        return this.type.defaultPoint;
    }

    public List<Position> getPath(Position src, Position target) {
        additionalCheck(src, target);

        Direction direction = direction(src, target);

        if (!directionList.contains(direction)) {
            throw new IllegalArgumentException();
        }

        return src.getPath(direction, target);
    }

    protected void additionalCheck(Position src, Position target) {
        return;
    }


    abstract Direction direction(Position src, Position target);

    protected Degree degree(Position src, Position target) {
        return src.degree(target);
    }

    public boolean isSameTeam(Piece other) {
        return (this.isWhite() && other.isWhite()) ||
                (this.isBlack() && other.isBlack());
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
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

}
