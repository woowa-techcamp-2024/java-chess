package chess.pieces;

import chess.Position;

public class Queen extends Piece{
    protected Queen(Color color, Position position) {
        super(color, Type.QUEEN.getRepresentation(color), position, Type.QUEEN.getDefaultPoint());
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
