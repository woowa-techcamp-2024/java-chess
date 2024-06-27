package com.seong.chess.pieces;

import com.seong.chess.Position;
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

    public static Piece createBlank(Position position) {
        return Blank.create();
    }

    public static Piece createBlank() {
        return Blank.create();
    }

    public static Piece createWhitePawn(Position position) {
        return Pawn.createWhite();
    }

    public static Piece createWhitePawn() {
        return Pawn.createWhite();
    }

    public static Piece createBlackPawn() {
        return Pawn.createBlack();
    }

    public static Piece createWhiteKing() {
        return King.createWhite();
    }

    public static Piece createBlackKing() {
        return King.createBlack();
    }

    public static Piece createWhiteQueen() {
        return Queen.createWhite();
    }

    public static Piece createBlackQueen() {
        return Queen.createBlack();
    }

    public static Piece createWhiteRook() {
        return Rook.createWhite();
    }

    public static Piece createBlackRook() {
        return Rook.createBlack();
    }

    public static Piece createWhiteBishop() {
        return Bishop.createWhite();
    }

    public static Piece createBlackBishop() {
        return Bishop.createBlack();
    }

    public static Piece createWhiteKnight() {
        return Knight.createWhite();
    }

    public static Piece createBlackKnight() {
        return Knight.createBlack();
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

    public abstract Position nextPosition(String sourcePosition, Direction direction, int moveCount);

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
