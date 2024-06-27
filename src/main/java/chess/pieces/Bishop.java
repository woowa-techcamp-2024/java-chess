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
    public boolean canMove(Piece target) {
        if (isSameColor(target)) return false;

        Position targetPosition = target.getPosition();
        int tx = Math.abs(this.getPosition().getFile() - targetPosition.getFile());
        int ty = Math.abs(this.getPosition().getRank() - targetPosition.getRank());

        return tx == ty;
    }
}
