package piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    private Pawn pawn;

    @Test
    @DisplayName("생성자에 전달된 색의 폰이 생성되어야 한다")
    public void create() {
        final String WHITE = "white";
        final String BLACK = "black";

        pawn = new Pawn(WHITE);
        verifyPawn(WHITE);

        pawn = new Pawn(BLACK);
        verifyPawn(BLACK);
    }

    private void verifyPawn(final String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
