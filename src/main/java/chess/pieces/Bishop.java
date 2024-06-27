package chess.pieces;

import chess.Position;

public class Bishop extends Piece{
    protected Bishop(Color color, Position position) {
        super(color, Type.BISHOP.getRepresentation(color), position, Type.BISHOP.getDefaultPoint());
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
