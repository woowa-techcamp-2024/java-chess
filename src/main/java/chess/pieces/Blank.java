package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class Blank extends Piece {
    public Blank(Position position) {
        super(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    public static Blank create(Position position) {
        return new Blank(position);
    }

    @Override
    public Piece copyWithPosition(Position position) {
        return copyWithPosition(position);
    }

    @Override
    public boolean canMove(Piece target) {
        return false;
    }
}
