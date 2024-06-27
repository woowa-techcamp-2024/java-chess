package java_chess.chess.pieces.values;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LocationTest {

    @MethodSource
    @ParameterizedTest
    @DisplayName("위치를 생성한다.")
    void create(int x, char y) {
        var actualResult = Location.of(x, y);
        assertThat(actualResult.getX()).isEqualTo(x);
        assertThat(actualResult.getCurrentY()).isEqualTo(y);
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("잘못된 좌표가 입력되면 위치 생성에 실패한다.")
    void createFail(int x, char y) {
        assertThatThrownBy(() -> Location.of(x, y))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid Location Input");
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("문자열을 기반으로 위치를 생성한다.")
    void createViaString(String locationStr) {
        var actualResult = Location.from(locationStr);
        assertThat(actualResult.getX()).isEqualTo(Integer.parseInt(locationStr.substring(1)));
        assertThat(actualResult.getCurrentY()).isEqualTo(locationStr.charAt(0));
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("잘못된 문자열이 입력되면 위치 생성에 실패한다.")
    void createViaStringFail(String locationStr) {
        assertThatThrownBy(() -> Location.from(locationStr))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid Location Input");
    }

    static Stream<Arguments> createViaString() {
        return Stream.of(
            Arguments.of("a1"),
            Arguments.of("b3"),
            Arguments.of("h8")
        );
    }

    static Stream<Arguments> createViaStringFail() {
        return Stream.of(
            Arguments.of("1a"),
            Arguments.of("3b"),
            Arguments.of("8h")
        );
    }

    static Stream<Arguments> create() {
        return Stream.of(
            Arguments.of(1, 'a'),
            Arguments.of(3, 'b'),
            Arguments.of(8, 'h')
        );
    }

    static Stream<Arguments> createFail() {
        return Stream.of(
            Arguments.of(1, 'z'),
            Arguments.of(0, 'a'),
            Arguments.of(-1, 'h'),
            Arguments.of(11, 'H')
        );
    }
}