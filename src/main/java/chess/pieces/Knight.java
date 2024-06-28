package chess.pieces;

import chess.Position;

import java.util.Set;

public class Knight extends Piece {
    private static final Set<Position> dirs = Set.of(new Position(2, 1), new Position(2, -1), new Position(-2, 1), new Position(-2, -1), new Position(1, 2), new Position(-1, 2), new Position(1, -2), new Position(-1, -2));

    protected Knight(Color color, Position position) {
        super(color, Type.KNIGHT.getRepresentation(color), position, Type.KNIGHT.getDefaultPoint());
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
