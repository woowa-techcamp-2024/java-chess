package chess.pieces;

import chess.Position;

import java.util.Set;

public class Bishop extends Piece{
    private static final Set<Position> dirs = Set.of(new Position(1, 1), new Position(1, -1), new Position(-1, -1), new Position(-1, 1));

    protected Bishop(Color color, Position position) {
        super(color, Type.BISHOP.getRepresentation(color), position, Type.BISHOP.getDefaultPoint());
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
