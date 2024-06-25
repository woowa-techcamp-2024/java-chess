package chess.pieces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PawnTest {

    @DisplayName("흰색/검은색 폰이 생성되어야한다.")
    @ParameterizedTest
    @ValueSource(strings = {"white", "black"})
    void create(String color) {
        Pawn pawn = new Pawn(color);
        verifyPawn(pawn, color);
    }

    private void verifyPawn(final Pawn pawn, final String expectedColor) {
        Assertions.assertThat(pawn.getColor()).isEqualTo(expectedColor);
    }

    @Test
    public void create_기본생성자() {
        Pawn pawn = new Pawn();
        org.junit.jupiter.api.Assertions.assertEquals("white", pawn.getColor());
    }
}