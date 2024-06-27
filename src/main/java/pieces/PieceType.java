package pieces;

import static pieces.Color.WHITE;

import java.util.List;
import utils.StringUtils;

public enum PieceType {

	PAWN('P', 1.0),
	KNIGHT('N', 2.5),
	ROOK('R', 5.0),
	BISHOP('B', 3.0),
	QUEEN('Q', 9.0),
	KING('K', 0.0),
	BLANK('.', 0.0);

	private final char representation;
	private final double defaultPoint;

	PieceType(char representation, double defaultPoint) {
		this.representation = representation;
		this.defaultPoint = defaultPoint;
	}

	public static PieceType from(int row, int column) {
		if (row == 1 || row == 6) {
			return PAWN;
		}
		if (row >= 2 && row <= 5) {
			return BLANK;
		}
		if (column == 0 || column == 7) {
			return ROOK;
		}
		if (column == 1 || column == 6) {
			return KNIGHT;
		}
		if (column == 2 || column == 5) {
			return BISHOP;
		}
		if (column == 3) {
			return QUEEN;
		}
		return KING;
	}

	private static List<PieceType> getPromotionPieceTypes() {
		return List.of(KNIGHT, ROOK, QUEEN, BISHOP);
	}

	public static PieceType from(String input) {
		return getPromotionPieceTypes().stream()
			.filter(pieceType -> StringUtils.equalsIgnoreCase(pieceType.name(), input))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("invalid PieceType."));
	}

	public char getRepresentation(Color color) {
		return color == WHITE ? Character.toLowerCase(representation) : representation;
	}

	public double getDefaultPoint() {
		return defaultPoint;
	}
}
