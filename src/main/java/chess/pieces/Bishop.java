package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class Bishop extends Piece {
    private Bishop(Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    public static Bishop create(Color color, Position position) {
        return new Bishop(color, position);
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
