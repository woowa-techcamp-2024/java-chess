import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void 흰색_폰_생성() {
        final String color = "white";
        Pawn pawn = new Pawn(color);
        assertThat(pawn.verifyPawn(color)).isTrue();
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다.")
    public void 검은색_폰_생성() {
        final String color = "black";
        Pawn pawn = new Pawn(color);
        assertThat(pawn.verifyPawn(color)).isTrue();
    }

    @Test
    public void 기본_폰_생성() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals("white", pawn.getColor());
    }
}
