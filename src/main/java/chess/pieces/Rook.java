package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class Rook extends Piece {
    private Rook(Color color, Position position) {
        super(color, Type.ROOK, position);
    }

    public static Rook create(Color color, Position position) {
        return new Rook(color, position);
    }

    @Override
    public Piece copyWithPosition(Position position) {
        return create(this.getColor(), position);
    }

    @Override
    public boolean canMove(Piece target) {
        return false;
    }
}
