package chess;

import java.util.List;

import pieces.Color;
import pieces.Piece;
import pieces.PieceType;

public record Rank(
	List<Piece> pieces
) {

	public Piece getPiece(int column) {
		return pieces.get(column);
	}
}
