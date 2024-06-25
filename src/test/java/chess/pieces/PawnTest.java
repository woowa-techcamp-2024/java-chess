package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("기본 생성자는 흰색 폰을 생성하고 표현한다.")
    @Test
    void create_기본생성자() {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }

    @DisplayName("흰색/검은색 폰을 생성하고 표현한다.")
    @ParameterizedTest
    @MethodSource
    void create(final String color, final char representation) {
        verifyPawn(color, representation);
    }

    private static Stream<Arguments> create() {
        return Stream.of(
                Arguments.of(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION),
                Arguments.of(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION)
        );
    }

    private void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }
}