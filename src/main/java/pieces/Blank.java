package pieces;

import chess.Position;

public class Blank extends Piece{
    public Blank(PieceColor color, PieceType type, Position position) {
        super(color, type, position);
    }

    @Override
    public boolean canMove(Piece targetPiece) {
        return false;
    }
}
