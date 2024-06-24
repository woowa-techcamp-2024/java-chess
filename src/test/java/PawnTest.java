import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        Pawn pawn = new Pawn("white");
        assertEquals("white",pawn.getColor());

        Pawn blackPawn = new Pawn("black");
        assertEquals("black",blackPawn.getColor());
    }
}
