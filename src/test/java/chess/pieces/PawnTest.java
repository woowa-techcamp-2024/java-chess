package chess.pieces;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void 흰색_폰_생성() {
        final Pawn.Color color = Pawn.Color.WHITE;
        Pawn pawn = new Pawn(color);
        assertThat(pawn.verifyPawn(color)).isTrue();
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다.")
    public void 검은색_폰_생성() {
        final Pawn.Color color = Pawn.Color.BLACK;
        Pawn pawn = new Pawn(color);
        assertThat(pawn.verifyPawn(color)).isTrue();
    }

    @Test
    public void 기본_폰_생성() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.Color.WHITE, pawn.getColor());
        assertEquals(Pawn.Color.WHITE.getRepresentation(), pawn.getRepresentation());
    }

    @Test
    public void 생성() {
        verifyPawn(Pawn.Color.WHITE, Pawn.Color.WHITE.getRepresentation());
        verifyPawn(Pawn.Color.BLACK, Pawn.Color.BLACK.getRepresentation());
    }

    void verifyPawn(Pawn.Color color, final char representation) {
        final Pawn pawn = new Pawn(color);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }
}
