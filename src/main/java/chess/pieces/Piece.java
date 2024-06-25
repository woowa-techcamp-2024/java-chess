package chess.pieces;

import chess.pieces.enums.Symbol;
import chess.pieces.values.Location;

public abstract class Piece {

    protected Symbol symbol;
    protected Location location;

    public void moveLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
