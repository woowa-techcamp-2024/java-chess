package lass9436.chess.pieces;

import java.util.List;

import lass9436.chess.Position;

public class NoPiece extends Piece {
	public NoPiece(Piece.Type type, Piece.Color color, Piece.Representation representation) {
		super(type, color, representation);
	}

	@Override
	public List<List<Position>> getPossibleMoves(Position currentPosition) {
		return List.of(List.of());
	}
}
