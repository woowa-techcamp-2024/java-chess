package woowa.camp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void create() {
        Pawn pawnWhite = new Pawn("white");
        Assertions.assertThat(pawnWhite.getColor()).isEqualTo("white");

        Pawn pawnBlack = new Pawn("black");
        Assertions.assertThat(pawnBlack.getColor()).isEqualTo("black");
    }

}
