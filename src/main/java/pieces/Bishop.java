package pieces;

import chess.Position;

public class Bishop extends Piece {

    public Bishop(Color color, PieceType pieceType, Position position) {
        super(color, pieceType, position);
    }

    @Override
    void move() {
        // 대각선으로만 원하는 만큼 갈 수 있다.
    }
}
