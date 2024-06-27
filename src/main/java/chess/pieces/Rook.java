package chess.pieces;

import chess.Position;

public class Rook extends Piece{
    protected Rook(Color color, Position position) {
        super(color, Type.ROOK.getRepresentation(color), position, Type.ROOK.getDefaultPoint());
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
