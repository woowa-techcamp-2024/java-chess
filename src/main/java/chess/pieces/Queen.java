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
    public boolean canMove(Piece target) {
        if (isSameColor(target)) return false;

        Position targetPosition = target.getPosition();
        int tx = Math.abs(this.getPosition().getFile() - targetPosition.getFile());
        int ty = Math.abs(this.getPosition().getRank() - targetPosition.getRank());
        return tx == 0 || ty == 0 || tx == ty;
    }
}
