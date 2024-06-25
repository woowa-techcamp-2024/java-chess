package chess.pieces;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰, 검은색 폰이 생성되어야 한다.")
    public void create(){
        verifyPawn(Pawn.BLACK_COLOR);
        verifyPawn(Pawn.WHITE_COLOR);
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다.")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
    }

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
