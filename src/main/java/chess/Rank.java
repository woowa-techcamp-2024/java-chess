package chess;

import java.util.List;
import pieces.Piece;

public record Rank(List<Piece> pieces) {

	public Piece getPiece(int column) {
		return pieces.get(column);
	}
}
