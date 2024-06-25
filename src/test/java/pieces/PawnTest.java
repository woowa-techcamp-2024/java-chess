package pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PawnTest {

    public static final String WHITE = "white";

    @Test
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(WHITE, pawn.getColor().name().toLowerCase());
    }
}
