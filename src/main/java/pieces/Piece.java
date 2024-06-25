package pieces;

import static pieces.Piece.Color.BLACK;
import static pieces.Piece.Color.WHITE;

import java.util.Objects;

public class Piece {

    private final Color color;
    private final PieceType pieceType;

    private Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public static Piece createPiece(Color color, PieceType pieceType) {
        return new Piece(color, pieceType);
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public char getRepresentation() {
        return WHITE.equals(color) ? Character.toLowerCase(pieceType.getRepresentation())
            : pieceType.getRepresentation();
    }

    public boolean isBlack() {
        return BLACK.equals(color);
    }

    public boolean isWhite() {
        return WHITE.equals(color);
    }

    public boolean isBlank() {
        return pieceType == PieceType.BLANK;
    }

    public boolean isPawn() {
        return pieceType == PieceType.PAWN;
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
        return color == piece.color && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }

    public enum Color {
        WHITE,
        BLACK,
        BLANK;
    }

    public enum PieceType {

        PAWN('P', 1.0),
        KNIGHT('N', 2.5),
        ROOK('R', 5.0),
        BISHOP('B', 3.0),
        QUEEN('Q', 9.0),
        KING('K', 0.0),
        BLANK('.', 0.0);

        private final char representation;
        private final double defaultPoint;

        PieceType(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getRepresentation() {
            return representation;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }

        public char getWhiteRepresentation() {
            return Character.toLowerCase(representation);
        }

        public char getBlackRepresentation() {
            return representation;
        }
    }
}