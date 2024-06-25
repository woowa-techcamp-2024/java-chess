import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    private void verifyPawn(final String color){
        Pawn pawn = new Pawn(color);
        assertEquals(color,pawn.getColor());
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        String white = "white";
        String black = "black";

        verifyPawn(white);
        verifyPawn(black);
    }
}
