package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;

public class Knight extends Piece {

    private static final Direction[] DIRECTIONS = new Direction[]{Direction.KNIGHT_LEFT_UP,
        Direction.KNIGHT_LEFT_DOWN, Direction.KNIGHT_DOWN_LEFT, Direction.KNIGHT_DOWN_RIGHT,
        Direction.KNIGHT_RIGHT_DOWN, Direction.KNIGHT_RIGHT_UP, Direction.KNIGHT_UP_RIGHT,
        Direction.KNIGHT_UP_LEFT};

    Knight(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_KNIGHT : Symbol.WHITE_KNIGHT, 2.5);
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
