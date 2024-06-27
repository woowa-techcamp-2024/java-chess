package org.example.chess.pieces;

import java.util.List;
import org.example.chess.board.Position;
import org.example.chess.pieces.enums.Direction;

public class King extends Piece {

    public King(Color color) {
        super(color, Type.KING);
    }

    @Override
    public boolean isValidMove(String source, String destination) {
        Position from = new Position(source);
        Position to = new Position(destination);

        Position delta = to.delta(from);
        List<Direction> directions = Direction.kingDirection();
        for (Direction direction : directions) {
            if (direction.isMovableDirection(delta)) {
                return true;
            }
        }
        return false;
    }
}
