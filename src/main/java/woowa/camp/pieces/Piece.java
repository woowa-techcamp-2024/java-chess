package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.NONE;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.NO_PIECE;

import java.util.List;
import java.util.Objects;

public class Piece {

    private final Type type;
    private final Color color;

    private Piece(final Type type, final Color color) {
        this.type = type;
        this.color = color;
    }

    public static Piece createPiece(final Type type, final Color color) {
        return new Piece(type, color);
    }

    public static Piece createWhitePieceOf(final Type type) {
        return new Piece(type, WHITE);
    }

    public static Piece createBlackPieceOf(final Type type) {
        return new Piece(type, BLACK);
    }

    public static Piece createBlank() {
        return new Piece(NO_PIECE, NONE);
    }

    public String getRepresentation() {
        return type.getRepresentation(color);
    }

    public Type getType() {
        return type;
    }

    public boolean isPieceOf(final Type type) {
        return this.type == type;
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public double getDefaultScore() {
        return this.type.getDefaultScore();
    }

    public enum Type {
        PAWN("p", 1.0),
        ROOK("r", 5.0),
        KNIGHT("n", 2.5),
        BISHOP("b", 3.0),
        QUEEN("q", 9.0),
        KING("k", 0.0),
        NO_PIECE(".", 0.0);

        private final String representation;
        private final double defaultScore;

        Type(String representation, double defaultScore) {
            this.representation = representation;
            this.defaultScore = defaultScore;
        }

        public static double calculateScore(final List<Piece> file) {
            double score = 0.0;
            int pawnCount = 0;
            for (Piece piece : file) {
                if (piece.isPieceOf(PAWN)) {
                    pawnCount++;
                    continue;
                }
                score += piece.type.defaultScore;
            }
            if (pawnCount > 1) {
                score += pawnCount * (PAWN.defaultScore / 2);
                return score;
            }
            score += pawnCount * PAWN.defaultScore;
            return score;
        }

        public double getDefaultScore() {
            return defaultScore;
        }

        public String getRepresentation(final Color color) {
            if (color == BLACK) {
                return representation.toUpperCase();
            }
            return representation;
        }
    }

    public enum Color {
        BLACK, WHITE, NONE
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
        return type == piece.type && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "type=" + type +
                ", color=" + color +
                '}';
    }
}
