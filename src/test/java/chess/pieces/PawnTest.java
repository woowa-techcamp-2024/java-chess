package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    private void verifyPawn(final String color,final char representation){
        Pawn pawn = new Pawn(color,representation);
        assertEquals(color,pawn.getColor());
        assertEquals(representation,pawn.getRepresentation());
    }

    @Test
    public void create() {
        String white = Pawn.WHITE_COLOR;
        String black = Pawn.BLACK_COLOR;
        verifyPawn(white,Pawn.WHITE_REPRESENTATION);
        verifyPawn(black,Pawn.BLACK_REPRESENTATION);
    }

    @Test
    public void create_기본생성자() throws Exception{
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR,pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION,pawn.getRepresentation());
    }
}
