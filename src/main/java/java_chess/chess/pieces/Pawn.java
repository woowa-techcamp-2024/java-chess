package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;

public class Pawn extends Piece {

    private static final Direction[] BLACK_DIRECTIONS = new Direction[]{Direction.DOWN};
    private static final Direction[] WHITE_DIRECTIONS = new Direction[]{Direction.UP};

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
