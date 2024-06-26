package com.woowatechcamp.chess.pieces;

public class Piece {
    private final Color color;
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }

    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public static Piece createBlank() {
        return new Piece(Color.NONE, Type.BLANK);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public char getRepresentation() {
        if (color == Color.WHITE) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public boolean isNotBlank() {
        return type != Type.BLANK;
    }

    public boolean isSameTypeAndColor(Type type, Color color) {
        return this.type == type && this.color == color;
    }

    @Override
    public String toString() {
        return String.valueOf(getRepresentation());
    }

    public enum Color {
        BLACK("black"),
        WHITE("white"),
        NONE("none");

        private final String color;

        Color(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    public enum Type {
        PAWN ('♙', '♟', 1.0),
        KNIGHT('♘', '♞', 2.5),
        BISHOP('♗', '♝', 3.0),
        ROOK('♖', '♜', 5.0),
        QUEEN('♕', '♛', 9.0),
        KING('♔', '♚', 0.0),
        BLANK('.', '.', 0.0);

        private final char whiteRepresentation;
        private final char blackRepresentation;
        private final double point;

        Type(char whiteRepresentation, char blackRepresentation, double point) {
            this.whiteRepresentation = whiteRepresentation;
            this.blackRepresentation = blackRepresentation;
            this.point = point;
        }

        public char getWhiteRepresentation() {
            return whiteRepresentation;
        }

        public char getBlackRepresentation() {
            return blackRepresentation;
        }

        public double getPoint() {
            return point;
        }
    }
}
