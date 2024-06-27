package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class Pawn extends Piece {
    private Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
    }

    public static Pawn create(Color color, Position position) {
        return new Pawn(color, position);
    }

    @Override
    public Piece copyWithPosition(Position position) {
        return create(this.getColor(), position);
    }

    @Override
    public boolean canMove(Piece target) {
        // TODO Pawn에 맞게 구현
        Position targetPosition = target.getPosition();

        int ty = Math.abs(this.getPosition().getFile() - targetPosition.getFile());
        int tx = Math.abs(this.getPosition().getRank() - targetPosition.getRank());

        return ty <= 1 && tx <= 1 && (tx + ty) == 1;
    }
}
