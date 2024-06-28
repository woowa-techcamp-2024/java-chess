package pieces;

import chess.Position;

public class Bishop extends Piece{
    public Bishop(PieceColor color, PieceType type, Position position) {
        super(color, type, position);
    }

    @Override
    public boolean canMove(Piece targetPiece) {
        Position targetPosition = targetPiece.getPosition();
        if (!isOurTeam(targetPiece)){
            int dx = Math.abs(this.getPosition().getX() - targetPosition.getX());
            int dy = Math.abs(this.getPosition().getY() - targetPosition.getY());
            return dx == dy;
        }
        return false;
    }
}
