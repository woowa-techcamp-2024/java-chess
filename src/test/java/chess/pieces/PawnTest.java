package chess.pieces;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰, 검은색 폰이 생성되어야 한다.")
    public void create() {
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다.")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }

    private void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }

}
