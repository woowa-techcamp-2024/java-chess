package pieces;

import static pieces.Color.WHITE;

import java.util.Arrays;
import utils.StringUtils;

public enum PieceType {

	PAWN('♙', 1.0),
	KNIGHT('♘', 2.5),
	ROOK('♖', 5.0),
	BISHOP('♗', 3.0),
	QUEEN('♕', 9.0),
	KING('♔', 0.0),
	BLANK(' ', 0.0);

	private static final int UNICODE_OFFSET = 6;
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

	public static String[] getPromotionPieceTypes() {
		return new String[]{KNIGHT.name(), ROOK.name(), QUEEN.name(), BISHOP.name()};
	}

	public static PieceType from(String input) {
		return Arrays.stream(getPromotionPieceTypes())
			.filter(pieceType -> StringUtils.equalsIgnoreCase(pieceType, input))
			.map(PieceType::valueOf)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("invalid PieceType."));
	}

	public char getRepresentation(Color color) {
		if (this == BLANK) {
			return representation;
		}
		return color == WHITE ? representation : (char) (representation + UNICODE_OFFSET);
	}

	public double getDefaultPoint() {
		return defaultPoint;
	}
}
