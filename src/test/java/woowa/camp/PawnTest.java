package woowa.camp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void create() {
        String white = "white";
        String black = "black";

        Pawn pawnWhite = new Pawn(white);
        assertThat(pawnWhite.getColor()).isEqualTo(white);

        Pawn pawnBlack = new Pawn(black);
        assertThat(pawnBlack.getColor()).isEqualTo(black);
    }

}
