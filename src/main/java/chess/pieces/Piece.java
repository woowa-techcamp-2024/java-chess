package chess.pieces;

import java.util.Objects;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN("p", "P"), KNIGHT("n","N"), BISHOP("b","B"), ROOK("r","R"), QUEEN("q","Q"), KING("k","K"), NO_PIECE("X","X");

        private final String whitRepresentation;
        private final String blackRepresentation;

        Type(String whitRepresentation, String blackRepresentation) {
            this.whitRepresentation = whitRepresentation;
            this.blackRepresentation = blackRepresentation;
        }

        public String getWhiteRepresentation() {
            return whitRepresentation;
        }

        public String getBlackRepresentation() {
            return blackRepresentation;
        }
    }

    private final String color;
    private final String representation;

    private Piece(String color, String representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createBlank() {
        return Piece.of(Color.NOCOLOR.name(), Type.NO_PIECE.getBlackRepresentation());
    }
    public static Piece createWhitePawn() {
        return Piece.of(Color.WHITE.name(), Type.PAWN.getWhiteRepresentation());
    }

    public static Piece createBlackPawn() {
        return Piece.of(Color.BLACK.name(), Type.PAWN.getBlackRepresentation());
    }

    public static Piece createWhiteKnight() {
        return Piece.of(Color.WHITE.name(), Type.KNIGHT.getWhiteRepresentation());
    }

    public static Piece createBlackKnight() {
        return Piece.of(Color.BLACK.name(), Type.KNIGHT.getBlackRepresentation());
    }

    public static Piece createWhiteQueen() {
        return Piece.of(Color.WHITE.name(), Type.QUEEN.getWhiteRepresentation());
    }

    public static Piece createBlackQueen() {
        return Piece.of(Color.BLACK.name(), Type.QUEEN.getBlackRepresentation());
    }

    public static Piece createWhiteKing() {
        return Piece.of(Color.WHITE.name(), Type.KING.getWhiteRepresentation());
    }

    public static Piece createBlackKing() {
        return Piece.of(Color.BLACK.name(), Type.KING.getBlackRepresentation());
    }

    public static Piece createWhiteRook() {
        return Piece.of(Color.WHITE.name(), Type.ROOK.getWhiteRepresentation());
    }

    public static Piece createBlackRook() {
        return Piece.of(Color.BLACK.name(), Type.ROOK.getBlackRepresentation());
    }

    public static Piece createWhiteBishop() {
        return Piece.of(Color.WHITE.name(), Type.BISHOP.getWhiteRepresentation());
    }

    public static Piece createBlackBishop() {
        return Piece.of(Color.BLACK.name(), Type.BISHOP.getBlackRepresentation());
    }

    public static  Piece of(final String color, final String representation) {
        return new Piece(color, representation);
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return representation;
    }

    public boolean isBlack() {
        return Objects.equals(color, Color.BLACK.name());
    }

    public boolean isWhite() {
        return Objects.equals(color, Color.WHITE.name());
    }
}
