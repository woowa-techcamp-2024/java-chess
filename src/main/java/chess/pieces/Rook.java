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
    public boolean canMove(Piece target) {
        if (isSameColor(target)) return false;

        Position targetPosition = target.getPosition();
        int tx = Math.abs(this.getPosition().getFile() - targetPosition.getFile());
        int ty = Math.abs(this.getPosition().getRank() - targetPosition.getRank());
        return tx == 0 || ty == 0;
    }
}
