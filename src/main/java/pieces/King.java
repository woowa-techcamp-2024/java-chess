package pieces;

import chess.Position;

public class King extends Piece {

    public King(Color color, PieceType pieceType, Position position) {
        super(color, pieceType, position);
    }

    @Override
    void move() {
        // 어느 방향이든 한 칸만 이동하고, 자신 스스로 체크메이트 되는 자리로 이동할 수 없음

    }
}
