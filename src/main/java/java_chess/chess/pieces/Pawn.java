package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;

public class Pawn extends Piece {

    private static final Direction[] BLACK_DIRECTIONS = new Direction[]{Direction.LEFT_DOWN,
        Direction.RIGHT_DOWN, Direction.DOWN, Direction.PAWN_BLACK_DOUBLE_DOWN};
    private static final Direction[] WHITE_DIRECTIONS = new Direction[]{Direction.LEFT_UP,
        Direction.RIGHT_UP, Direction.UP, Direction.PAWN_WHITE_DOUBLE_UP};

    Pawn(Color color) {
        super(color, color.equals(Color.BLACK) ? Symbol.BLACK_PAWN : Symbol.WHITE_PAWN, 1.0);
    }

    @Override
    public Direction[] getDirections() {
        return color.equals(Color.BLACK) ? BLACK_DIRECTIONS : WHITE_DIRECTIONS;
    }

    @Override
    public boolean canMoveMultipleTimes() {
        return false;
    }

}
