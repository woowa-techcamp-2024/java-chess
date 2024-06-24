package chess.piece;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        Pawn whitePawn = new Pawn("white");
        assertThat(whitePawn.getColor()).isEqualTo("white");

        Pawn blackPawn = new Pawn("white");
        assertThat(blackPawn.getColor()).isEqualTo("white");
    }
}

