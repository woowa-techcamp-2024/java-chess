package org.example.chess.pieces;

import org.example.chess.pieces.global.Direction;
import org.example.chess.pieces.global.MoveSeq;
import org.example.chess.pieces.global.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece{
    private final Color color;
    private final Type type;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isBlank() { return this.type == Type.NO_PIECE; }

    public boolean isExist() { return this.type != Type.NO_PIECE; }

    public boolean isPawn() { return this.type == Type.PAWN; }

    public Color getColor() {
        return color;
    }

    public Type getName() {
        return type;
    }

    public char getRepresentation() {
        if (isWhite()) {
            return Character.toLowerCase(this.type.representation);
        }

        return Character.toUpperCase(this.type.representation);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }

    @Override
    public String toString(){
        return String.format("Color: %s, Type: %s \n", color, type);
    }

    public abstract List<MoveSeq> getMoveSeqs();

    public enum Color {
        WHITE, BLACK, NOCOLOR
    }

    public enum Type {
        PAWN('P', 1.0),
        KNIGHT('N', 2.5),
        ROOK('R', 5.0),
        BISHOP('B', 3.0),
        QUEEN('Q', 9.0),
        KING('K', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getRepresentation() {
            return this.representation;
        }

        public double getDefaultPoint() {
            return this.defaultPoint;
        }
    }
}
