package chess;

import static chess.Board.BOARD_SIZE;

import java.util.Objects;

public class Position {

	public static final int POSITION_INPUT_SIZE = 2;
	public static final int MINIMUM_POSITION_BOUND = 0;
	public static final int MAXIMUM_POSITION_BOUND = BOARD_SIZE - 1;

	private int row;
	private int column;

	private Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public static Position of(int row, int column) {
		return new Position(row, column);
	}

	public static Position of(String position) {
		int row = BOARD_SIZE - Character.getNumericValue(position.charAt(1));
		int col = position.charAt(0) - 'a';
		validatePosition(position, row, col);
		return new Position(row, col);
	}

	public static boolean isInvalidRange(int row, int col) {
		return row < MINIMUM_POSITION_BOUND || row > MAXIMUM_POSITION_BOUND
			|| col < MINIMUM_POSITION_BOUND || col > MAXIMUM_POSITION_BOUND;
	}

	private static void validatePosition(String position, int row, int col) {
		if (position.length() != POSITION_INPUT_SIZE || isInvalidRange(row, col)) {
			throw new IllegalArgumentException("invalid position input, position: " + position + ".");
		}
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public void addDirection(Direction direction) {
		this.row += direction.getX();
		this.column += direction.getY();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Position position = (Position) o;
		return row == position.row && column == position.column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}
}
