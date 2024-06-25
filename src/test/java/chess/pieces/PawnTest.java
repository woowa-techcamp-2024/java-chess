package chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    @Test
    @DisplayName("폰이 생성되어야 한다")
    public void create() {
        verifyPawn(Colors.WHITE);
        verifyPawn(Colors.BLACK);
    }

    @Test
    @DisplayName("폰 생성시 인자가 없다면 흰색으로 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals("WHITE", pawn.getColor().name());
    }

    private void verifyPawn(final Colors color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
