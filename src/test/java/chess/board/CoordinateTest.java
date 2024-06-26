package chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoordinateTest {

    @DisplayName("좌표를 반대로 입력한 경우 예외가 발생한다.")
    @Test
    void createCoordinateWithReversedOrder() {
        // given
        String coordinate = "1e";

        // when & then
        assertThatThrownBy(() -> new Coordinate(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표는 알파벳과 숫자의 순서로 이루어져야 합니다.");
    }


    @DisplayName("해당하는 좌표의 Coordinate 객체를 반환한다")
    @Test
    void createCoordinate() {
        // given
        String coordinateStr = "a1";

        // when
        Coordinate coordinate = new Coordinate(coordinateStr);

        // then
        assertThat(coordinate).isNotNull()
                .extracting("rankIndex", "widthIndex")
                .containsExactly(7, 0);
    }

    @DisplayName("알파벳 좌표의 범위를 벗어난 경우 예외가 발생한다")
    @Test
    void createCoordinateWithInvalidAlphabetRange() {
        // given
        String coordinate = "i1";

        // when & then
        assertThatThrownBy(() -> new Coordinate(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("범위를 넘어선 좌표입니다.");
    }

    @DisplayName("올바르지 않은 형식의 좌표를 입력한 경우 예외가 발생한다")
    @Test
    void createCoordinateWithInvalidFormat() {
        // given
        Board board = new Board();
        String coordinate = "i11";

        // when & then
        assertThatThrownBy(() -> new Coordinate(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표는 2글자여야 합니다.");
    }
    @DisplayName("빈 좌표를 입력한 경우 예외가 발생한다")
    @Test
    void findPieceWithEmptyCoordinate() {
        // given
        String coordinate = "";

        // when & then
        assertThatThrownBy(() -> new Coordinate(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표를 입력해주세요.");
    }

    @DisplayName("숫자 좌표의 범위를 벗어난 경우 예외가 발생한다")
    @Test
    void findPieceWithInvalidNumericRange() {
        // given
        String coordinate = "a9";

        // when & then
        assertThatThrownBy(() -> new Coordinate(coordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("범위를 넘어선 좌표입니다.");
    }

}