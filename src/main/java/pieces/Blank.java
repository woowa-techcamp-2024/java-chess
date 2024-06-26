package pieces;

import chess.Position;

public class Blank extends Piece {

    public Blank(Color color, PieceType pieceType, Position position) {
        super(color, pieceType, position);
    }

    @Override
    void move() {

    }
}
