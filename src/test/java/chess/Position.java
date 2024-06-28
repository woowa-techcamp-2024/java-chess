package chess;

import java.util.Objects;

public class Position {
	private final int row;
	private final int col;

	public Position(String position) {
		if (position.length() != 2)
			throw new IllegalArgumentException("position length must be 2");
		col = position.charAt(0) - 'a';
		row = 8 - Integer.parseInt(position.substring(1));
	}

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Position position))
			return false;
		return row == position.row && col == position.col;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, col);
	}
}
