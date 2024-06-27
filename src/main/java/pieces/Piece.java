package pieces;

import static java.lang.Math.abs;
import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

import chess.Direction;
import chess.Position;
import java.util.List;
import utils.MathUtils;
import view.ChessGUI;

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

	public char getRepresentation() {
		return pieceType.getRepresentation(color);
	}

	public Direction getFinalDirection(List<Direction> directions, Position source, Position target,
									   PieceType pieceType) {
		return directions.stream()
			.filter(direction -> isSameDirection(pieceType, direction,
				target.getRow() - source.getRow(), target.getColumn() - source.getColumn()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("invalid target position."));
	}

	public void doPromotion(Position position) {
		if (canPromotion(position)) {
			updatePieceType(PieceType.from(ChessGUI.inputPromotion()));
		}
	}

	public void validateTurn(int turn) {
		if ((color == WHITE && turn != 0) || (color == BLACK && turn != 1)) {
			throw new IllegalArgumentException("invalid turn.");
		}
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

	private boolean isMultipleMovePieceType(PieceType pieceType) {
		return pieceType == PieceType.BISHOP || pieceType == PieceType.QUEEN || pieceType == PieceType.ROOK;
	}

	private boolean canPromotion(Position position) {
		return pieceType == PieceType.PAWN && (position.getRow() == 7 || position.getRow() == 0);
	}

	private void updatePieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public Color getColor() {
		return color;
	}

	public PieceType getPieceType() {
		return pieceType;
	}
}