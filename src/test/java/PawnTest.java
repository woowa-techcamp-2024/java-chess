
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    @Test
    @DisplayName("폰이 생성되어야 한다")
    public void create() {
        final String WHITE_COLOR = "white";
        final String BLACK_COLOR = "black";

        verifyPawn(WHITE_COLOR);
        verifyPawn(BLACK_COLOR);
    }

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
