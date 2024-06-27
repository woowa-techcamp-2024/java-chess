package chess.pieces;

import chess.Position;

public class Knight extends Piece {
    protected Knight(Color color, Position position) {
        super(color, Type.KNIGHT.getRepresentation(color), position, Type.KNIGHT.getDefaultPoint());
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
