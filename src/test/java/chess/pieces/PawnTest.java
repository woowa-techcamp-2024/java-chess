package chess.pieces;

import chess.pieces.enums.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @MethodSource
    @ParameterizedTest
    @DisplayName("폰이 생성되어야 한다")
    void create(Color color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.verifyPawn(color)).isTrue();
    }

    private static Stream<Arguments> create() {
        return Stream.of(
                Arguments.of(Color.WHITE),
                Arguments.of(Color.BLACK)
        );
    }

}