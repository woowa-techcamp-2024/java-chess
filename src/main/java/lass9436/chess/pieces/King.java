package lass9436.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import lass9436.chess.Position;

public class King extends Piece {

	public King(Type type, Color color, Representation representation) {
		super(type, color, representation);
	}

	@Override
	public List<List<Position>> getPossibleMoves(Position currentPosition) {
		List<List<Position>> possibleMoves = new ArrayList<>();
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.UP, 1));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.DOWN, 1));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT, 1));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT, 1));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_UP, 1));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_DOWN, 1));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_UP, 1));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_DOWN, 1));
		return possibleMoves;
	}
}
