package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;

public class Bishop extends Piece {

    private static final Direction[] DIRECTIONS = new Direction[]{
        Direction.LEFT_UP, Direction.LEFT_DOWN, Direction.RIGHT_UP, Direction.RIGHT_DOWN
    };

    Bishop(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_BISHOP : Symbol.WHITE_BISHOP, 3);
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
