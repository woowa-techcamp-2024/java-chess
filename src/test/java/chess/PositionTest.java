package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

public class PositionTest {

	@ParameterizedTest
	@CsvSource({"a8,0,0", "b8,0,1", "c8,0,2", "a7,1,0", "b7,1,1", "c7,1,2"})
	void ofTest(String s, int row, int column) {
		Position a8 = Position.of(s);
		assertThat(a8.getRow()).isEqualTo(row);
		assertThat(a8.getColumn()).isEqualTo(column);
	}

	@ParameterizedTest
	@EnumSource(Direction.class)
	void addDirectionTest(Direction d) {
		Position position = Position.of(4, 4);
		int row = d.getX();
		int column = d.getY();
		position.addDirection(d);
		assertThat(position.getRow()).isEqualTo(4 + row);
		assertThat(position.getColumn()).isEqualTo(4 + column);
	}
}
