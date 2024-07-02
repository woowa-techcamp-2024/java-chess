package chess.pieces;

import chess.board.Coordinate;

import java.util.List;

public class Blank extends Piece {

    @Override
    public boolean verifyMoveCoordinate(Coordinate from, Coordinate to) {
        return false;
    }

    @Override
    public List<Coordinate> canMoveCoordinate(Coordinate from) {
        return List.of();
    }

    public static Blank createBlank() {
        return new Blank(Color.NOCOLOR);
    }

    private Blank(Color color) {
        super(color, Type.NO_PIECE);
    }

}
