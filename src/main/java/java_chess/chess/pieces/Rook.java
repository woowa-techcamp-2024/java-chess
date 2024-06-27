package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;

public class Rook extends Piece {

    private static final Direction[] DIRECTIONS = new Direction[]{
        Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN
    };

    Rook(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_ROOK : Symbol.WHITE_ROOK, 5.0);
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
