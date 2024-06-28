package java_chess.chess.pieces.values;

import java_chess.chess.pieces.Piece;

public class Move {

    private final Location from;
    private final Location to;
    private final Piece piece;

    public Move(Location from, Location to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }


}
