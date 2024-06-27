package pieces;

import static java.lang.Math.abs;

import chess.Direction;
import chess.Position;
import java.util.List;
import utils.MathUtils;

public record Piece(Color color, PieceType pieceType) {

	public static Piece of(Color color, PieceType pieceType) {
		return new Piece(color, pieceType);
	}

	public boolean isSameColor(Color color) {
		return this.color == color;
	}

	public boolean isSamePieceType(PieceType pieceType) {
		return this.pieceType == pieceType;
	}

	public char getRepresentation() {
		return pieceType.getRepresentation(color);
	}

	public Direction getFinalDirection(List<Direction> directions, Position source, Position target,
									   PieceType pieceType) {
		return directions.stream()
			.filter(direction -> isSameDirection(pieceType, direction,
				target.getRow() - source.getRow(), target.getColumn() - source.getColumn()))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	private boolean isSameDirection(PieceType pieceType, Direction direction, int rowDiff, int columnDiff) {
		if (isMultipleMovePieceType(pieceType)) {
			int gcd = MathUtils.gcd(abs(rowDiff), abs(columnDiff));
			if (gcd != 0) {
				rowDiff /= gcd;
				columnDiff /= gcd;
			}
		}
		return direction.getX() == rowDiff && direction.getY() == columnDiff;
	}

	/**
	 * 하나의 Direction으로 끝까지 갈 수 있는 pieceType BISHOP, QUEEN, ROOK
	 */
	private boolean isMultipleMovePieceType(PieceType pieceType) {
		return pieceType == PieceType.BISHOP || pieceType == PieceType.QUEEN || pieceType == PieceType.ROOK;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Piece piece = (Piece) o;
		return color == piece.color && pieceType == piece.pieceType;
	}

}