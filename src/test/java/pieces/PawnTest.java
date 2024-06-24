package pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @MethodSource
    @ParameterizedTest
    @DisplayName("폰이 생성되어야 한다")
    void create(String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.verifyPawn(color)).isTrue();
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("잘못된 색상이 입력된 경우 폰은 생성되지 말아야 한다.")
    void createFailed(String wrongColor) {
        assertThatThrownBy(() -> new Pawn(wrongColor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Color Input");
    }

    private static Stream<Arguments> create() {
        return Stream.of(
                Arguments.of("white"),
                Arguments.of("black")
        );
    }

    private static Stream<Arguments> createFailed() {
        return Stream.of(
                Arguments.of("whtte"),
                Arguments.of(""),
                Arguments.of("blank")
        );
    }
}