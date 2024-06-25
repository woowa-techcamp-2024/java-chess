package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;
import chess.pieces.values.Location;

public abstract class Piece {

    protected Symbol symbol;
    protected Location location;

    public void moveLocation(Location location) {
        this.location = location;
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
}
