package pieces;

import chess.Position;

public class Knight extends Piece{
    public Knight(PieceColor color, PieceType type, Position position) {
        super(color, type, position);
    }

    @Override
    public boolean canMove(Piece targetPiece) {
        Position targetPosition = targetPiece.getPosition();
        if(!this.isOurTeam(targetPiece)){
            int dx = Math.abs(this.getPosition().getX() - targetPosition.getX());
            int dy = Math.abs(this.getPosition().getY() - targetPosition.getY());
            return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
        }
        return false;
    }
}
