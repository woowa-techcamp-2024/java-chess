package woowa.camp.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("[Success] 색상에 맞는 폰이 생성되어야 한다")
    void create() {
        Pawn pawnWhite = new Pawn(Color.PAWN_WHITE);
        Pawn pawnBlack = new Pawn(Color.PAWN_BLACK);

        verifyPawnColor(pawnWhite, Color.PAWN_WHITE.getName(), Color.PAWN_WHITE.getRepresentation());
        verifyPawnColor(pawnBlack, Color.PAWN_BLACK.getName(), Color.PAWN_BLACK.getRepresentation());

        assertThat(pawnWhite.getRepresentation()).isEqualTo(Color.PAWN_WHITE.getRepresentation());
        assertThat(pawnBlack.getRepresentation()).isEqualTo(Color.PAWN_BLACK.getRepresentation());
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Pawn pawn = new Pawn();
        verifyPawnColor(pawn, Color.PAWN_WHITE.getName(), Color.PAWN_WHITE.getRepresentation());
    }

    private void verifyPawnColor(final Pawn pawn, final String color, final String representation) {
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }

}
