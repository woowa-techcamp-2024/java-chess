package pieces;

import chess.Position;

public class King extends Piece{
    public King(PieceColor color, PieceType type, Position position) {
        super(color, type, position);
    }

    @Override
    public boolean canMove(Piece targetPiece) {
        Position targetPosition = targetPiece.getPosition();
        if(!isOurTeam(targetPiece)){
            int dx = Math.abs(this.getPosition().getX() - targetPosition.getX());
            int dy = Math.abs(this.getPosition().getY() - targetPosition.getY());
            return (dx <= 1 && dy <= 1);
        }
        return false;
    }
}
