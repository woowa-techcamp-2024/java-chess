package chess.pieces;

import java.util.Objects;

public class Piece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    public static final String WHITE_PAWN_REPRESENTATION = "p";
    public static final String BLACK_PAWN_REPRESENTATION = "P";

    public static final String WHITE_KNIGHT_REPRESENTATION = "n";
    public static final String BLACK_KNIGHT_REPRESENTATION = "N";

    public static final String WHITE_QUEEN_REPRESENTATION = "q";
    public static final String BLACK_QUEEN_REPRESENTATION = "Q";

    public static final String WHITE_KING_REPRESENTATION = "k";
    public static final String BLACK_KING_REPRESENTATION = "K";

    public static final String WHITE_ROOK_REPRESENTATION = "r";
    public static final String BLACK_ROOK_REPRESENTATION = "R";

    public static final String WHITE_BISHOP_REPRESENTATION = "b";
    public static final String BLACK_BISHOP_REPRESENTATION = "B";

    private final String color;
    private final String representation;

    private Piece(String color, String representation) {
        this.color = color;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return Piece.of(Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return Piece.of(Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return Piece.of(Piece.WHITE_COLOR, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return Piece.of(Piece.BLACK_COLOR, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return Piece.of(Piece.WHITE_COLOR, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return Piece.of(Piece.BLACK_COLOR, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return Piece.of(Piece.WHITE_COLOR, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return Piece.of(Piece.BLACK_COLOR, BLACK_KING_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return Piece.of(Piece.WHITE_COLOR, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return Piece.of(Piece.BLACK_COLOR, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return Piece.of(Piece.WHITE_COLOR, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return Piece.of(Piece.BLACK_COLOR, BLACK_BISHOP_REPRESENTATION);
    }

    public static  Piece of(final String color, final String representation) {
        return new Piece(color, representation);
    }

    public String getColor() {
        return color;
    }

    public String getRepresentation() {
        return representation;
    }

    public boolean isBlack() {
        return Objects.equals(color, BLACK_COLOR);
    }

    public boolean isWhite() {
        return Objects.equals(color, WHITE_COLOR);
    }
}
