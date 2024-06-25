package chess.pieces;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    @Test
    @DisplayName("색상에 맞는 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    private void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }

    @Test
    @DisplayName("기본 생성자로 생성 시 white 폰이 생성되어야 한다")
    public void create_base_constructor() {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }
}