package chess.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        String white = "white";
        String black = "black";

        verifyPawn(white);

        verifyPawn(black);
    }

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);

        Assertions.assertThat(pawn.getColor()).isEqualTo(color);
    }
}