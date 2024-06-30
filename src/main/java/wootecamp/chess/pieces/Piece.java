package wootecamp.chess.pieces;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    public enum Color {
        WHITE, BLACK, EMPTY;

        public Color getOppositeColor() {
            if (this == EMPTY) return EMPTY;
            if (this == WHITE) return BLACK;
            if (this == BLACK) return WHITE;
            throw new IllegalArgumentException("잘못된 색상입니다.");
        }
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        EMPTY('.', 0.0);

        private final char representation;
        private final double point;

        Type(final char representation, double point) {
            this.representation = representation;
            this.point = point;
        }

        public char getRepresentation() {
            return representation;
        }

        public double getPoint() {
            return point;
        }
    }

    protected final Type type;
    protected final Color color;

    protected Piece(Color color, Type type) {
        this.type = type;
        this.color = color;
    }

    public List<BoardPosition> findAllMovablePositions(Board board, BoardPosition source) {
        List<MovableDirection> movableDirections = getMovableDirections();
        List<BoardPosition> movablePositions = new ArrayList<>();

        for (MovableDirection movableDirection : movableDirections) {
            BoardPosition curPosition = source;
            for (int i = 0; i < movableDirection.times() && curPosition.canCreateNextPosition(movableDirection.direction()); i++) {
                curPosition = curPosition.createNextPosition(movableDirection.direction());
                Piece curPiece = board.findPiece(curPosition);
                if (!curPiece.isEmptyPiece()) {
                    break;
                }

                movablePositions.add(curPosition);
            }
        }

        return movablePositions;
    }

    public abstract List<MovableDirection> getMovableDirections();

    public abstract boolean canJump();


    public boolean isSamePiece(Color color, Type type) {
        return this.color == color && this.type == type;
    }

    public boolean isEmptyPiece() {
        return false;
    }

    public boolean isKing(final Color color) {
        return this.type == Type.KING && this.color == color;
    }

    public boolean isPawn(Color color) {
        return this.type == Type.PAWN && this.color == color;
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        char representation = type.getRepresentation();
        if (this.color == Color.BLACK) {
            return Character.toUpperCase(representation);
        }
        return representation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return type == piece.type && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }
}
