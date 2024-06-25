package chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("해당하는 색 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
    }

    @Test
    public void create_기본생성자() {
        chess.pieces.Pawn pawn = new chess.pieces.Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
    }

    public void verifyPawn(final String color, final String representation) {
        chess.pieces.Pawn pawn = new chess.pieces.Pawn(color, representation);
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }
}
