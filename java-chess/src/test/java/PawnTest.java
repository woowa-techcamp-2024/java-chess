import org.example.character.Pawn;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        String white = "white";
        String black = "black";

        Pawn whitePawn = new Pawn(white);
        Pawn blackPawn = new Pawn(black);

        assertThat(whitePawn.getColor()).isEqualTo(white);
        assertThat(blackPawn.getColor()).isEqualTo(black);
    }
}