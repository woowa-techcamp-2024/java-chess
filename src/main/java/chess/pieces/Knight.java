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
    public boolean canMove(Piece target) {
        if (isSameColor(target)) return false;

        Position targetPosition = target.getPosition();
        int ty = Math.abs(this.getPosition().getFile() - targetPosition.getFile());
        int tx = Math.abs(this.getPosition().getRank() - targetPosition.getRank());

        return (tx == 1 && ty == 2) || (tx == 2 && ty == 1);
    }
}
