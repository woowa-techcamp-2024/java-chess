package wootecamp.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PawnTest {
    @Test
    @DisplayName("생성자 파라미터에 색깔을 전달해서 폰을 생성한다.")
    void create() {
        Pawn whitePawn = new Pawn("white");
        Pawn blackPawn = new Pawn("black");

        assertThat(whitePawn.getColor()).isEqualTo("white");
        assertThat(blackPawn.getColor()).isEqualTo("black");
    }
}
