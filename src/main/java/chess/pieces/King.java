package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Direction;
import chess.pieces.enums.Symbol;

public class King extends Piece {

    private static final Direction[] DIRECTIONS = new Direction[]{
        Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN,
        Direction.LEFT_UP, Direction.LEFT_DOWN, Direction.RIGHT_UP, Direction.RIGHT_DOWN
    };

    King(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_KING : Symbol.WHITE_KING, 0);
    }

    @Override
    public Direction[] getDirections() {
        return DIRECTIONS;
    }

    @Override
    public boolean canMoveMultipleTimes() {
        return false;
    }
}
