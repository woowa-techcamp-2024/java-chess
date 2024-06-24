package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    private static final String WHITE = "white";
    private static final String BLACK = "black";

    @Test
    @DisplayName("흰색/검정 폰이 생성되어야 한다")
    public void create()
    {
        Pawn pawn = new Pawn(WHITE);
        verifyPawn(pawn, WHITE);

        pawn = new Pawn(BLACK);
        verifyPawn(pawn, BLACK);
    }

    private void verifyPawn(final Pawn pawn, final String color){
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
