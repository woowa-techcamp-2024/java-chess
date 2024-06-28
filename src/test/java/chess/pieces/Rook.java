package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Position;

public class Rook extends Piece {
	public Rook(Type type, Color color, Representation representation) {
		super(type, color, representation);
	}

	@Override
	public List<List<Position>> getPossibleMoves(Position currentPosition) {
		List<List<Position>> possibleMoves = new ArrayList<>();
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.LEFT, 0));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.RIGHT, 0));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.UP, 0));
		possibleMoves.add(getPossibleMovesOfDirection(currentPosition, Direction.DOWN, 0));
		return possibleMoves;
	}
}
