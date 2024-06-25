package chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    @Test
    @DisplayName("폰이 생성되어야 한다")
    public void create() {
        verifyPawn(Colors.WHITE, Representations.WHITE_PAWN);
        verifyPawn(Colors.BLACK, Representations.BLACK_PAWN);
    }

    @Test
    @DisplayName("폰 생성시 인자가 없다면 흰색으로 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Colors.WHITE);
        assertThat(pawn.getRepresentation()).isEqualTo(Representations.WHITE_PAWN);
    }

    private void verifyPawn(final Colors color, final Representations representation) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }
}
