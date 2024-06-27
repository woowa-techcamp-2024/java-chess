package chess.pieces;

import chess.board.Coordinate;

public class Blank extends Piece {

    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {
        return false;
    }

    public static Blank createBlank() {
        return new Blank(Color.NOCOLOR);
    }

    private Blank(Color color) {
        super(color, Type.NO_PIECE);
    }

}
