package chess.pieces;

import chess.Position;

public class NoPiece extends Piece {
    protected NoPiece(Color color, Position position) {
        super(color, Type.NO_PIECE.getRepresentation(color), position, Type.NO_PIECE.getDefaultPoint());
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
