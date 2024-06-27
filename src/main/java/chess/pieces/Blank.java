package chess.pieces;

import chess.Position;
import chess.constant.Color;
import chess.constant.Direction;
import chess.constant.Type;

public class Blank extends Piece {
    protected Blank(Position position) {
        super(Color.NO_COLOR, Type.NO_PIECE, position);
    }

    public static Piece create(Position position) {
        return new Blank(position);
    }

    @Override
    public boolean verifyMovePosition(final Piece piece) {
        return false;
    }

    @Override
    public Direction getDirection(Position position) {
        return null;
    }
}
