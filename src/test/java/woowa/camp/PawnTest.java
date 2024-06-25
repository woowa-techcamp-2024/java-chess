package woowa.camp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("[Success] 색상에 맞는 폰이 생성되어야 한다")
    void create() {
        Pawn pawnWhite = new Pawn(Pawn.WHITE_COLOR);
        Pawn pawnBlack = new Pawn(Pawn.BLACK_COLOR);

        verifyPawnColor(pawnWhite, Pawn.WHITE_COLOR);
        verifyPawnColor(pawnBlack, Pawn.BLACK_COLOR);
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Pawn pawn = new Pawn();
        verifyPawnColor(pawn, Pawn.WHITE_COLOR);
    }

    private void verifyPawnColor(final Pawn pawn, final String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
