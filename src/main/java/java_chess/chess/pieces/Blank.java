package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;

public class Blank extends Piece {

    private static final Direction[] DIRECTIONS = new Direction[0];
    static final Blank INSTANCE = new Blank();

    private Blank() {
        super(Color.NONE, Symbol.BLANK, 0);
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
