package chess;

import static pieces.Color.*;

import java.util.ArrayList;
import java.util.List;

import pieces.Piece;

public enum DirectionType {
	NORTH(-1, 0),
	NORTHEAST(-1, 1),
	EAST(0, 1),
	SOUTHEAST(1, 1),
	SOUTH(1, 0),
	SOUTHWEST(1, -1),
	WEST(0, -1),
	NORTHWEST(-1, -1),

	NN(-2, 0),
	SS(2, 0),
	NNE(-2, 1),
	NNW(-2, -1),
	SSE(2, 1),
	SSW(2, -1),
	EEN(-1, 2),
	EES(1, 2),
	WWN(-1, -2),
	WWS(1, -2);

	private final int x;
	private final int y;

	DirectionType(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static List<DirectionType> findByPiece(Piece piece) {
		return switch (piece.getPieceType()) {
			case PAWN -> piece.isSameColor(BLACK) ? blackPawnDirection() : whitePawnDirection();
			case KNIGHT -> knightDirection();
			case ROOK -> rookDirection();
			case BISHOP -> bishopDirection();
			case QUEEN -> queenDirection();
			case KING -> kingDirection();
			default -> List.of();
		};
	}

	public static List<DirectionType> rookDirection() {
		return new ArrayList<>(List.of(NORTH, EAST, SOUTH, WEST));
	}

	public static List<DirectionType> bishopDirection() {
		return new ArrayList<>(List.of(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST));
	}

	public static List<DirectionType> queenDirection() {
		return new ArrayList<>(List.of(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST));
	}

	public static List<DirectionType> knightDirection() {
		return new ArrayList<>(List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS));
	}

	public static List<DirectionType> kingDirection() {
		return new ArrayList<>(List.of(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST));
	}

	private static List<DirectionType> whitePawnDirection() {
		return new ArrayList<>(List.of(NORTH));
	}

	private static List<DirectionType> blackPawnDirection() {
		return new ArrayList<>(List.of(SOUTH));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
