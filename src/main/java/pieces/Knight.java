package pieces;

import chess.Position;

public class Knight extends Piece {

    public Knight(Color color, PieceType pieceType, Position position) {
        super(color, pieceType, position);
    }

    @Override
    void move() {
        // 한 방향으로 2칸을 이동하고 직각 방향으로 1칸을 이동한다. 유일하게 기물을 뛰어넘을 수 있다.
    }
}
