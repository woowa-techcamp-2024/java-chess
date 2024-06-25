package org.example.chess.pieces;

public class Piece {


    private final String color;
    private final char representation;
    private final PieceType pieceType;

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';

    public Piece(String color, PieceType pieceType) {
        this.color = color;
        this.representation = color.equals(WHITE_COLOR) ? (char) (pieceType.getAbbreviation() - 32)  : pieceType.getAbbreviation();
        this.pieceType = pieceType;
    }

    public Piece() {
        this.representation = (char) (PieceType.PAWN.getAbbreviation()+32);
        System.out.println(representation);
        this.pieceType = PieceType.PAWN;
        this.color = WHITE_COLOR;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public static Piece createBlackPawn() {
        return new Piece(Piece.BLACK_COLOR, PieceType.PAWN);
    }
}
