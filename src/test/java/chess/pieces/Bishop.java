package chess.pieces;

import chess.Position;

import java.util.List;

public class Bishop extends Piece{
    public Bishop(Type type, Color color, Representation representation) {
        super(type, color, representation);
    }

    @Override
    public List<List<Position>> getPossibleMoves(Position currentPosition) {
        return List.of();
    }
}
