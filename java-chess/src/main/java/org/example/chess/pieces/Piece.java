package org.example.chess.pieces;

public class Piece {
    private final Color color;
    private final Name name;

    private Piece(Color color, Name name) {
        this.color = color;
        this.name = name;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

    public Name getName() {
        return name;
    }

    public String getRepresentation() {
        if (isWhite()) {
            return this.name.symbol.toLowerCase();
        }

        return this.name.symbol.toUpperCase();
    }

    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Name.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Name.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Name.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Name.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, Name.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, Name.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Name.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Name.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Name.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Name.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Name.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Name.KING);
    }

    public enum Color {
        WHITE, BLACK
    }

    public enum Name {
        PAWN("P"), KNIGHT("N"), ROOK("R"), BISHOP("B"), QUEEN("Q"), KING("K");

        private final String symbol;

        Name(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return this.symbol;
        }
    }
}
