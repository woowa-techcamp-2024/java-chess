package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;

public class Queen extends Piece {

    private static final Direction[] DIRECTIONS = new Direction[]{
        Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN,
        Direction.LEFT_UP, Direction.LEFT_DOWN, Direction.RIGHT_UP, Direction.RIGHT_DOWN
    };

    Queen(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_QUEEN : Symbol.WHITE_QUEEN, 9.0);
    }

    @Override
    public Direction[] getDirections() {
        return DIRECTIONS;
    }

    @Override
    public boolean canMoveMultipleTimes() {
        return true;
    }

}
