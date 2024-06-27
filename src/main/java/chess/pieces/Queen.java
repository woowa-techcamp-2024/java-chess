package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class Queen extends Piece {
    private Queen(Color color, Position position) {
        super(color, Type.QUEEN, position);
    }

    public static Queen create(Color color, Position position) {
        return new Queen(color, position);
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
