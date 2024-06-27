package chess.pieces;

import chess.CommandChanger;
import chess.Position;

import java.util.Objects;

public abstract class Piece {

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0), KNIGHT('n', 2.5), BISHOP('b', 3.0), ROOK('r', 5.0), QUEEN('q', 9.0), KING('k', 0.0), NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getRepresentation(Color color) {
            if (Objects.equals(color, Color.NOCOLOR)) {
                return Type.NO_PIECE.representation;
            }

            if (color == Color.BLACK) {
                return Character.toUpperCase(representation);
            }

            return representation;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }
    }

    private final Color color;
    private char representation;
    private Position position;
    private double defaultPoint;

    protected Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    protected Piece(final Color color, final char representation, final Position position, final double defaultPoint) {
        this.color = color;
        this.representation = representation;
        this.position = position;
        this.defaultPoint = defaultPoint;
    }


    public static Piece of(Class<? extends Piece> pieceClass, Color color) {
        try {
            return pieceClass.getDeclaredConstructor(Color.class, Position.class).newInstance(color, null);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Piece class: " + pieceClass.getName());
        }
    }

    public static Piece of(Class<? extends Piece> pieceClass, Color color, Position position) {
        try {
            return pieceClass.getDeclaredConstructor(Color.class, Position.class).newInstance(color, position);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Piece class: " + pieceClass.getName());
        }
    }

    public static Piece of(Class<? extends Piece> pieceClass, Color color, String position) {
        try {
            return pieceClass.getDeclaredConstructor(Color.class, Position.class).newInstance(color, CommandChanger.getPosition(position));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Piece class: " + pieceClass.getName());
        }
    }

    public String getColor() {
        return color.name();
    }

    public double getDefaultScore() {
        return defaultPoint;
    }

    public char getType() {
        return representation;
    }

    public boolean isBlack() {
        return Objects.equals(color, Color.BLACK);
    }

    public boolean isWhite() {
        return Objects.equals(color, Color.WHITE);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(String position) {
        this.position = CommandChanger.getPosition(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return representation == piece.representation && Double.compare(defaultPoint, piece.defaultPoint) == 0 && color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation, position, defaultPoint);
    }
}
