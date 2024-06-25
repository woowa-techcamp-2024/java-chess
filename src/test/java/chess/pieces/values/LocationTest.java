package chess.pieces.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocationTest {

    @MethodSource
    @ParameterizedTest
    @DisplayName("위치를 생성한다.")
    void create(int x, char y) {
        var actualResult = Location.of(x, y);
        assertThat(actualResult.getX()).isEqualTo(x);
        assertThat(actualResult.getY()).isEqualTo((int) y - 'a');
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("잘못된 좌표가 입력되면 위치 생성에 실패한다.")
    void createFail(int x, char y) {
        assertThatThrownBy(() -> Location.of(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Location Input");
    }

    private static Stream<Arguments> create() {
        return Stream.of(
                Arguments.of(1, 'a'),
                Arguments.of(3, 'b'),
                Arguments.of(8, 'h')
        );
    }

    private static Stream<Arguments> createFail() {
        return Stream.of(
                Arguments.of(1, 'z'),
                Arguments.of(0, 'a'),
                Arguments.of(-1, 'h'),
                Arguments.of(11, 'H')
        );
    }
}