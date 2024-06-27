package chess.pieces;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Type type, Color color, Representation representation) {
        super(type, color, representation);
    }

    @Override
    public List<List<Position>> getPossibleMoves(Position currentPosition) {
        List<List<Position>> possibleMoves = new ArrayList<>();
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_LEFT_UP, 1));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_LEFT_DOWN, 1));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_UP_UP, 1));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_DOWN_DOWN, 1));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_RIGHT_UP, 1));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_RIGHT_DOWN, 1));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_UP_UP, 1));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_DOWN_DOWN, 1));
        return possibleMoves;
    }
}
