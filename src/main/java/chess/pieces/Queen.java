package chess.pieces;

import chess.Position;

import java.util.Set;

public class Queen extends Piece{
    private static final Set<Position> dirs = Set.of(new Position(1, 0), new Position(0, 1), new Position(0, -1), new Position(1, 1), new Position(1, -1), new Position(-1, -1), new Position(-1, 0), new Position(-1, 1));

    protected Queen(Color color, Position position) {
        super(color, Type.QUEEN.getRepresentation(color), position, Type.QUEEN.getDefaultPoint());
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
