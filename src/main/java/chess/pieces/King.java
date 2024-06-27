package chess.pieces;

import chess.Position;

public class King extends Piece {
    protected King(Color color, Position position) {
        super(color, Type.KING.getRepresentation(color), position, Type.KING.getDefaultPoint());
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
