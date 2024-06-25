package chess.pieces;

import java.util.Objects;

public class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN("p", "P", 1.0), KNIGHT("n","N", 2.5), BISHOP("b","B", 3.0), ROOK("r","R", 5.0), QUEEN("q","Q", 9.0), KING("k","K", 0.0), NO_PIECE(".",".", 0.0);

        private final String whitRepresentation;
        private final String blackRepresentation;
        private final double defaultPoint;

        Type(String whitRepresentation, String blackRepresentation, double defaultPoint) {
            this.whitRepresentation = whitRepresentation;
            this.blackRepresentation = blackRepresentation;
            this.defaultPoint = defaultPoint;
        }

        public String getWhiteRepresentation() {
            return whitRepresentation;
        }

        public String getBlackRepresentation() {
            return blackRepresentation;
        }

        public double getDefaultPoint() {
            return defaultPoint;
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
