package woowa.camp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("색상에 맞는 폰이 생성되어야 한다")
    void create() {
        String white = "white";
        String black = "black";
        Pawn pawnWhite = new Pawn(white);
        Pawn pawnBlack = new Pawn(black);

        verifyPawnColor(pawnWhite, white);
        verifyPawnColor(pawnBlack, black);
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Pawn pawn = new Pawn();
        verifyPawnColor(pawn, "white");
    }

    private void verifyPawnColor(final Pawn pawn, final String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }

}
