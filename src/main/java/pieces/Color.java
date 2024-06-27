package pieces;

public enum Color {

	WHITE,
	BLACK,
	BLANK;

	public static Color from(int row) {
		if (row <= 1) {
			return BLACK;
		}
		if (row >= 6) {
			return WHITE;
		}
		return BLANK;
	}

	public static Color fromByTurn(int turn) {
		return turn == 0 ? WHITE : BLACK;
	}
}
