package chess.pieces;

import chess.Position;

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

        Type(String whiteRepresentation, String blackRepresentation, double defaultPoint) {
            this.whitRepresentation = whiteRepresentation;
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
    private final double defaultScore;
    private Position position;

    private Piece(String color, String representation, double defaultScore) {
        this.color = color;
        this.representation = representation;
        this.defaultScore = defaultScore;
    }

    public Piece(String color, String representation, double defaultScore, Position position) {
        this.color = color;
        this.representation = representation;
        this.defaultScore = defaultScore;
        this.position = position;
    }

    public static Piece createBlank() {
        return Piece.of(Color.NOCOLOR.name(), Type.NO_PIECE.getBlackRepresentation(), Type.NO_PIECE.getDefaultPoint());
    }

    public static Piece createBlank(final Position position) {
        return Piece.of(Color.NOCOLOR.name(), Type.NO_PIECE.getBlackRepresentation(), Type.NO_PIECE.getDefaultPoint(), position);
    }

    public static Piece createWhite(Type type) {
        return Piece.of(Color.WHITE.name(), type.getWhiteRepresentation(), type.getDefaultPoint());
    }

    public static Piece createBlack(Type type) {
        return Piece.of(Color.BLACK.name(), type.getBlackRepresentation(), type.getDefaultPoint());
    }


    public static  Piece of(final String color, final String representation, final double defaultPoint) {
        return new Piece(color, representation, defaultPoint);
    }

    public static Piece of(final String color, final String representation, final double defaultPoint, final Position position) {
        return new Piece(color, representation, defaultPoint, position);
    }

    public String getColor() {
        return color;
    }

    public double getDefaultScore() {
        return defaultScore;
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

    public static Piece createWhite(Type type, Position position) {
        return Piece.of(Color.WHITE.name(), type.getWhiteRepresentation(), type.getDefaultPoint(), position);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Double.compare(defaultScore, piece.defaultScore) == 0 && Objects.equals(color, piece.color) && Objects.equals(representation, piece.representation) && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation, defaultScore, position);
    }
}
