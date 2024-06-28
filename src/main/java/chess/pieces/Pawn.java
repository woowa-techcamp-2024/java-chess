package chess.pieces;

import chess.Position;

import java.util.Set;

public class Pawn extends Piece{
    private boolean isMoved = false;
    public static final Set<Position>  whiteDirs= Set.of(new Position(1, 0));
    public static final Set<Position> blackDirs = Set.of(new Position(-1, 0));
    protected Pawn(Color color, Position position) {
        super(color, Type.PAWN.getRepresentation(color), position, Type.PAWN.getDefaultPoint());
    }

    @Override
    public void setMoved() {
        isMoved = true;
    }

    @Override
    public boolean isMoved() {
        return isMoved;
    }

    @Override
    protected Set<Position> getDirs() {
        if (getColor() == Color.BLACK) {
            return blackDirs;
        }

        return whiteDirs;
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
