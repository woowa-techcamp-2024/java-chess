package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;

public abstract class Piece {

    protected Color color;
    protected Symbol symbol;

    Piece(Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        verifyBlank();
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return !isBlack();
    }

    public Color getColor() {
        return color;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol.getValue();
    }

    public static Piece createPawn(Color color) {
        return new Pawn(color);
    }

    public static Piece createBishop(Color color) {
        return new Bishop(color);
    }

    public static Piece createKnight(Color color) {
        return new Knight(color);
    }

    public static Piece createRook(Color color) {
        return new Rook(color);
    }

    public static Piece createQueen(Color color) {
        return new Queen(color);
    }

    public static Piece createKing(Color color) {
        return new King(color);
    }

    public static Piece getBlank() {
        return Blank.INSTANCE;
    }

    private void verifyBlank() {
        if (this instanceof Blank) {
            throw new RuntimeException("Blank Does not have Color");
        }
    }
}
