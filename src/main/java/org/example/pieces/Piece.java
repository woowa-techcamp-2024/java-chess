package org.example.pieces;

import static org.example.pieces.Piece.Color.BLACK;
import static org.example.pieces.Piece.Color.WHITE;

import java.util.Objects;
import org.example.chess.Position;

public abstract class Piece {

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(Type type) {
        return this.representation == type;
    }

    public double getPoint() {
        return representation.point;
    }

    public boolean isPawn() {
        return representation == Type.PAWN;
    }

    public enum Color {
        WHITE, BLACK, NO_COLOR;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Type {
        PAWN('♙', 1.0),
        ROOK('♖', 5.0),
        KNIGHT('♘', 2.5),
        BISHOP('♗', 3.0),
        QUEEN('♕', 9.0),
        KING('♔', 0.0),
        NO_PIECE('.', 0.0);  // 빈 문자로 설정

        private final char representation;
        private final double point;


        Type(char representation, double point) {
            this.representation = representation;
            this.point = point;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            final int offsetNumber = 6;
            if (this == NO_PIECE) {
                return representation;
            }

            return (char) (representation + offsetNumber);
        }
    }

    private final Color color;
    private final Type representation;
    private int moveCount = 0;

    Piece(Color color, Type representation) {
        this.color = color;
        this.representation = representation;
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return isWhite() ?
            representation.getWhiteRepresentation() : representation.getBlackRepresentation();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public boolean isWhite() {
        return color.equals(WHITE);
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece piece)) {
            return false;
        }
        return color == piece.color
            && representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation);
    }

    abstract boolean verifyMove(Position start, Position end);

    boolean verifyMove(String start, String end) {
        boolean ret = verifyMove(new Position(start), new Position(end));

        if (ret) {
            moveCount++;
        }
        return ret;
    }

}
