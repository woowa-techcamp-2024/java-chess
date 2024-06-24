package wootecamp.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PawnTest {
    @Test
    @DisplayName("생성자 파라미터에 색깔을 전달해서 폰을 생성한다.")
    void create() {
        final String WHITE_COLOR = "white";
        final String BLACK_COLOR = "black";

        verifyPawn(WHITE_COLOR);
        verifyPawn(BLACK_COLOR);
    }

    void verifyPawn(final String color) {
        final Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
