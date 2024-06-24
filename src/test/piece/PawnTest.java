package piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    private Pawn pawn;

    @Test
    @DisplayName("생성자에 전달된 색의 폰이 생성되어야 한다")
    public void create() {
        pawn = new Pawn(Color.WHITE);
        verifyPawn(Color.WHITE);

        pawn = new Pawn(Color.BLACK);
        verifyPawn(Color.BLACK);
    }

    private void verifyPawn(final Color color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
