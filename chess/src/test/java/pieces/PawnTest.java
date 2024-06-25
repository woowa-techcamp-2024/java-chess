package pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    private static final PawnColor WHITE = PawnColor.WHITE;
    private static final PawnColor BLACK = PawnColor.BLACK;

    @Test
    @DisplayName("흰색/검정 폰이 생성되어야 한다")
    public void create()
    {
        verifyPawn(WHITE);
        verifyPawn(BLACK);
    }

    @Test
    @DisplayName("폰 기본 생성자 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(WHITE, pawn.getColor());
    }



    private void verifyPawn(final PawnColor color){
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
