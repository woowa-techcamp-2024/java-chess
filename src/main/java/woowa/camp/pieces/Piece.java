package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Type.NO_PIECE;

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
        return new Piece(type, Color.WHITE);
    }

    public static Piece createBlackPieceOf(final Type type) {
        return new Piece(type, Color.BLACK);
    }

    public static Piece createBlank() {
        return new Piece(NO_PIECE, Color.NONE);
    }

    public String getColor() {
        return color.getName();
    }

    public Representation getRepresentation() {
        return Representation.findMatchedRepresentation(type, color);
    }

    public Type getType() {
        return type;
    }

    public boolean isPieceOf(final Type type) {
        return this.type == type;
    }

    public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isBlack() {
        return "black".equals(color.getName());
    }

    public boolean isWhite() {
        return "white".equals(color.getName());
    }

    public enum Type {
        PAWN("pawn"),
        ROOK("rook"),
        KNIGHT("knight"),
        BISHOP("bishop"),
        QUEEN("queen"),
        KING("king"),
        NO_PIECE("noPiece");

        private final String name;

        Type(String name) {
            this.name = name;
        }
    }

    public enum Color {
        BLACK("black"),
        WHITE("white"),
        NONE("none");

        private final String name;

        Color(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }


}
