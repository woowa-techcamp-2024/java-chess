package chess.pieces;

import chess.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Type type, Color color, Representation representation) {
        super(type, color, representation);
    }

    @Override
    public List<List<Position>> getPossibleMoves(Position currentPosition) {
        List<List<Position>> possibleMoves = new ArrayList<>();
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT, 0));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT, 0));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.UP, 0));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.DOWN, 0));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_UP, 0));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_UP, 0));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_DOWN, 0));
        possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_DOWN, 0));
        return possibleMoves;
    }
}
