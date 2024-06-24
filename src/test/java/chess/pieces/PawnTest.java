package chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("해당하는 색 폰이 생성되어야 한다")
    public void create() {
        verifyPawn("black");
        verifyPawn("white");
    }

    @Test
    public void create_기본생성자() throws Exception {
        chess.pieces.Pawn pawn = new chess.pieces.Pawn();
        assertEquals("white", pawn.getColor());
    }

    public void verifyPawn(final String color) {
        chess.pieces.Pawn pawn = new chess.pieces.Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
