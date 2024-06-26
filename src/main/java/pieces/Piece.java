package pieces;

import java.util.Objects;

public class Piece {

	private final Color color;
	private PieceType pieceType;

	public Piece(Color color, PieceType pieceType) {
		this.color = color;
		this.pieceType = pieceType;
	}

	public static Piece of(Color color, PieceType pieceType) {
		return new Piece(color, pieceType);
	}

	public boolean isSameColor(Color color) {
		return this.color == color;
	}

	public boolean isSamePieceType(PieceType pieceType) {
		return this.pieceType == pieceType;
	}

	public Color getColor() {
		return color;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public char getRepresentation() {
		return pieceType.getRepresentation(color);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Piece piece = (Piece)o;
		return color == piece.color && pieceType == piece.pieceType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, pieceType);
	}
}