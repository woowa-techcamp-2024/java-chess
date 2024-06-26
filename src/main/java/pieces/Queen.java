package pieces;

import chess.Position;

public class Queen extends Piece {

    public Queen(Color color, PieceType pieceType, Position position) {
        super(color, pieceType, position);
    }

    @Override
    void move() {
        // 어느 방향이든 끝까지 갈 수 있는데, 어떤 기물이든 뛰어넘을 수 없다.
    }
}
