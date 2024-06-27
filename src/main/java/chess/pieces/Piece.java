package chess.pieces;

import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Type type;


    public abstract boolean verifyMovePosition(String source, String dest);

    public final Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return color.equals(Color.BLACK) ? Character.toUpperCase(type.getRepresentation()) : type.getRepresentation();
    }

    public double getDefaultPoint() {
        return type.getPoint();
    }

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isSameColorAndType(Color color, Type type) {
        return this.color.equals(color) && this.type.equals(type);
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isSameType(Type type) {
        return this.type.equals(type);
    }

    public Type getType() {
        return type;
    }

    public boolean isBlank() {
        return this.type.equals(Type.NO_PIECE);
    }

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;


        public double getPoint() {
            return defaultPoint;
        }

        public char getRepresentation() {
            return representation;
        }

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }
    }
}
