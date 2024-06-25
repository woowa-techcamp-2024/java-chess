package chess.pieces;

import java.util.Objects;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN("p", "P"), KNIGHT("n","N"), BISHOP("b","B"), ROOK("r","R"), QUEEN("q","Q"), KING("k","K"), NO_PIECE(".",".");

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

    public static Piece createWhite(Type type) {
        return Piece.of(Color.WHITE.name(), type.getWhiteRepresentation());
    }

    public static Piece createBlack(Type type) {
        return Piece.of(Color.BLACK.name(), type.getBlackRepresentation());
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
