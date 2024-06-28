package lass9436.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import lass9436.chess.Position;

public class Pawn extends Piece {
	public Pawn(Type type, Color color, Representation representation) {
		super(type, color, representation);
	}

	@Override
	public List<List<Position>> getPossibleMoves(Position currentPosition) {
		List<List<Position>> possibleMoves = new ArrayList<>();
		if (Color.BLACK == getColor()) {
			if (currentPosition.getRow() == 2)
				possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.DOWN, 2));
			else
				possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.DOWN, 1));
			possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_DOWN, 1));
			possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_DOWN, 1));
		}
		if (Color.WHITE == getColor()) {
			if (currentPosition.getRow() == 6)
				possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.UP, 2));
			else
				possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.UP, 1));
			possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT_UP, 1));
			possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT_UP, 1));
		}
		return possibleMoves;
	}
}
