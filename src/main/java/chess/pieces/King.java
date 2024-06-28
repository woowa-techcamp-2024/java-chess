package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class King extends Piece {
    private King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    public static King create(Color color, Position position) {
        return new King(color, position);
    }

    @Override
    public boolean canMove(Piece target) {
        if (isSameColor(target)) return false;

        Position targetPosition = target.getPosition();
        int ty = Math.abs(this.getPosition().getFile() - targetPosition.getFile());
        int tx = Math.abs(this.getPosition().getRank() - targetPosition.getRank());

        return ty <= 1 && tx <= 1;
    }
}
