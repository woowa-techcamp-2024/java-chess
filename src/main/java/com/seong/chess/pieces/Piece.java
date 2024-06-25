package com.seong.chess.pieces;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;

    }

    public enum Type {
        PAWN('p'),
        KNIGHT('n'),
        BISHOP('b'),
        ROOK('r'),
        QUEEN('q'),
        KING('k'),
        NO_PIECE('.');

        private final char representation;

        Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

    }


    private final Type type;
    private final Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public static Piece createBlank() {
        return new Piece(Type.NO_PIECE, Color.NOCOLOR);
    }

    public static Piece createWhitePawn() {
        return new Piece(Type.PAWN, Color.WHITE);
    }

    public static Piece createBlackPawn() {
        return new Piece(Type.PAWN, Color.BLACK);
    }

    public static Piece createWhiteKing() {
        return new Piece(Type.KING, Color.WHITE);
    }

    public static Piece createBlackKing() {
        return new Piece(Type.KING, Color.BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Type.QUEEN, Color.WHITE);
    }

    public static Piece createBlackQueen() {
        return new Piece(Type.QUEEN, Color.BLACK);
    }

    public static Piece createWhiteRook() {
        return new Piece(Type.ROOK, Color.WHITE);
    }

    public static Piece createBlackRook() {
        return new Piece(Type.ROOK, Color.BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Type.BISHOP, Color.WHITE);
    }

    public static Piece createBlackBishop() {
        return new Piece(Type.BISHOP, Color.BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Type.KNIGHT, Color.WHITE);
    }

    public static Piece createBlackKnight() {
        return new Piece(Type.KNIGHT, Color.BLACK);
    }

    public char getRepresentation() {
        return isWhite() ? type.getWhiteRepresentation() : type.getBlackRepresentation();
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public String getColor() {
        return color.toString();
    }

    public Type getType() {
        return type;
    }
}
