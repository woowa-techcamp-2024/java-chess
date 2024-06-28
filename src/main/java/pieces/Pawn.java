package pieces;

import chess.Position;

public class Pawn extends Piece{
    public Pawn(PieceColor color, PieceType type, Position position) {
        super(color, type, position);
    }

    @Override
    public boolean canMove(Piece targetPiece) {
        Position targetPosition = targetPiece.getPosition();
        // 1: down, -1: up
        int direction = this.isWhite() ? -1 : 1;
        int startY = this.isWhite() ? 6 : 1;
        // 직진
        if(this.getPosition().getX() == targetPosition.getX()){
            if(targetPiece.getType().equals(PieceType.NO_PIECE)){
                // 한칸
                if(this.getPosition().getY() + direction == targetPosition.getY()){
                    return true;
                }
                // 처음 움직일 때만 두 칸 가능
                if(this.getPosition().getY() == startY && this.getPosition().getY()+2*direction == targetPosition.getY()){
                    return true;
                }
                return false;
            }
            return false;
        }
        // 대각 한칸
        return this.isEnemy(targetPiece) && Math.abs(this.getPosition().getX() - targetPosition.getX()) == 1 && this.getPosition().getY() + direction == targetPosition.getY();
    }
}
