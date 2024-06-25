package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("기본 생성자를 이용하면 흰색 폰이 생성되고, 기본 표현이 설정되어야 한다")

    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }

    @Test
    @DisplayName("생성자를 이용하면 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }
}
