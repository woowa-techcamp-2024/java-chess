package pieces;

import chess.Position;

public class Rook extends Piece {

    public Rook(Color color, PieceType pieceType, Position position) {
        super(color, pieceType, position);
    }

    @Override
    void move() {
        // 일직선으로만 갈 수 있고, 뛰어넘을 수 없다.
    }
}
