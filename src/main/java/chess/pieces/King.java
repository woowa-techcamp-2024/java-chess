package chess.pieces;

import chess.Position;

import java.util.Set;

public class King extends Piece {
    private static final Set<Position> dirs = Set.of(new Position(1, 0), new Position(0, 1), new Position(0, -1), new Position(1, 1), new Position(1, -1), new Position(-1, -1), new Position(-1, 0));

    protected King(Color color, Position position) {
        super(color, Type.KING.getRepresentation(color), position, Type.KING.getDefaultPoint());
    }

    @Override
    public Set<Position> getDirs() {
        return dirs;
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
