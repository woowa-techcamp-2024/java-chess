package chess.pieces;

import chess.Position;

public class Pawn extends Piece{
    protected Pawn(Color color, Position position) {
        super(color, Type.PAWN.getRepresentation(color), position, Type.PAWN.getDefaultPoint());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
