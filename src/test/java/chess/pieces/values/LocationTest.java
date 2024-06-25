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
    void create(char x, int y) {
        var actualResult = Location.of(x, y);
        assertThat(actualResult.getX()).isEqualTo(x);
        assertThat(actualResult.getY()).isEqualTo(y);
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("잘못된 좌표가 입력되면 위치 생성에 실패한다.")
    void createFail(char x, int y) {
        assertThatThrownBy(() -> Location.of(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Location Input");
    }

    private static Stream<Arguments> create() {
        return Stream.of(
                Arguments.of('a', 1),
                Arguments.of('b', 3),
                Arguments.of('h', 8)
        );
    }

    private static Stream<Arguments> createFail() {
        return Stream.of(
                Arguments.of('z', 1),
                Arguments.of('a', 0),
                Arguments.of('h', -1),
                Arguments.of('H', 11)
        );
    }
}