package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;
import chess.pieces.values.Location;

public abstract class Piece {

    protected Color color;
    protected Symbol symbol;
    protected Location location;

    Piece(Color color) {
        this.color = color;
    }

    public void moveLocation(Location location) {
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return symbol.getValue();
    }

    public static Piece createWhitePawn() {
        return new Pawn(Color.WHITE);
    }

    public static Piece createBlackPawn() {
        return new Pawn(Color.BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Bishop(Color.WHITE);
    }

    public static Piece createBlackBishop() {
        return new Bishop(Color.BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Knight(Color.WHITE);
    }

    public static Piece createBlackKnight() {
        return new Knight(Color.BLACK);
    }

    public static Piece createWhiteRook() {
        return new Rook(Color.WHITE);
    }

    public static Piece createBlackRook() {
        return new Rook(Color.BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Queen(Color.WHITE);
    }

    public static Piece createBlackQueen() {
        return new Queen(Color.BLACK);
    }

    public static Piece createWhiteKing() {
        return new King(Color.WHITE);
    }

    public static Piece createBlackKing() {
        return new King(Color.BLACK);
    }

    public static Piece createBlank() {
        return new Blank();
    }

    private void verifyBlank() {
        if (this instanceof Blank) {
            throw new RuntimeException("Blank Does not have Color");
        }
    }
}
