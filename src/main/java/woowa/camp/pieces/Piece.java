package woowa.camp.pieces;

import static woowa.camp.pieces.Piece.Type.BISHOP;
import static woowa.camp.pieces.Piece.Type.KING;
import static woowa.camp.pieces.Piece.Type.KNIGHT;
import static woowa.camp.pieces.Piece.Type.PAWN;
import static woowa.camp.pieces.Piece.Type.QUEEN;
import static woowa.camp.pieces.Piece.Type.ROOK;

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

    public static Piece createWhitePawn() {
        return new Piece(PAWN, Color.WHITE);
    }

    public static Piece createBlackPawn() {
        return new Piece(PAWN, Color.BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Piece(KNIGHT, Color.WHITE);
    }

    public static Piece createBlackKnight() {
        return new Piece(KNIGHT, Color.BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(BISHOP, Color.WHITE);
    }

    public static Piece createBlackBishop() {
        return new Piece(BISHOP, Color.BLACK);
    }

    public static Piece createWhiteRook() {
        return new Piece(ROOK, Color.WHITE);
    }

    public static Piece createBlackRook() {
        return new Piece(ROOK, Color.BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Piece(QUEEN, Color.WHITE);
    }

    public static Piece createBlackQueen() {
        return new Piece(QUEEN, Color.BLACK);
    }

    public static Piece createWhiteKing() {
        return new Piece(KING, Color.WHITE);
    }

    public static Piece createBlackKing() {
        return new Piece(KING, Color.BLACK);
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
