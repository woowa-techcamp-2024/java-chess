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

    @Test
    @DisplayName("생성자에 색이 전달되지 않을 경우 흰색 폰이 생성되어야 한다")
    public void create_defaultConstructor() {
        pawn = new Pawn();
        verifyPawn(Color.WHITE);
    }

    private void verifyPawn(final Color color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
