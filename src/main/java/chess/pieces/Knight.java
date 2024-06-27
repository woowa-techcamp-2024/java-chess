package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class Knight extends Piece {
    private Knight(Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    public static Knight create(Color color, Position position) {
        return new Knight(color, position);
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
