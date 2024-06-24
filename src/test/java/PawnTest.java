import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        String white = "white";
        String black = "black";

        Pawn pawn = new Pawn(white);
        Pawn pawn2 = new Pawn(black);
        verifyPawn(pawn, white);
        verifyPawn(pawn2, black);
    }

    public void verifyPawn(Pawn pawn, final String color) {
        assertThat(pawn.color()).isEqualTo(color);
    }
}
