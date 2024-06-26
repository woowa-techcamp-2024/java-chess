package chess.pieces;

import java.util.Objects;

public class Piece {

    public static final String BLACK_COLOR = "black";
    public static final String WHITE_COLOR = "white";

    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char WHITE_ROOK_REPRESENTATION = 'r';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char WHITE_KING_REPRESENTATION = 'k';

    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    public static final char BLACK_ROOK_REPRESENTATION = 'R';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char BLACK_KING_REPRESENTATION = 'K';

    private String color;
    private String name;
    private char representation;

    public static Piece createWhitePawn() {
        return new Piece(Piece.WHITE_COLOR, "pawn", WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Piece.WHITE_COLOR, "knight", Piece.WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(Piece.WHITE_COLOR, "rook", Piece.WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Piece.WHITE_COLOR, "bishop", Piece.WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Piece.WHITE_COLOR, "queen", Piece.WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(Piece.WHITE_COLOR, "king", Piece.WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(Piece.BLACK_COLOR, "pawn", BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(Piece.BLACK_COLOR, "knight", BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(Piece.BLACK_COLOR, "rook", BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(Piece.BLACK_COLOR, "bishop", BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(Piece.BLACK_COLOR, "queen", BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(Piece.BLACK_COLOR, "king", BLACK_KING_REPRESENTATION);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Piece piece = (Piece) obj;
        if (color.equals(piece.color) && representation == piece.representation)
            return true;
        else
            return false;
    }

    public Piece(String color, String name, char representation) {
        this.color = color;
        this.name = name;
        this.representation = representation;
    }

    public String getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public char getRepresentation() {
        return this.representation;
    }

}