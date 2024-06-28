package lass9436.chess.pieces;

import java.util.List;

import lass9436.chess.Position;

public class Bishop extends Piece {
	public Bishop(Type type, Color color, Representation representation) {
		super(type, color, representation);
	}

	@Override
	public List<List<Position>> getPossibleMoves(Position currentPosition) {
		return List.of();
	}
}
