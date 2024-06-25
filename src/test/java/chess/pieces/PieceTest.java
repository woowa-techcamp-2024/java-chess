package chess.pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    private void verifyPawn(final String color,final char representation){
        Piece pawn = new Piece(color);
        assertEquals(color,pawn.getColor());
        assertEquals(representation,pawn.getRepresentation());
    }

    @Test
    public void create() {
        String white = Piece.WHITE_COLOR;
        String black = Piece.BLACK_COLOR;
        verifyPawn(white, Piece.WHITE_REPRESENTATION);
        verifyPawn(black, Piece.BLACK_REPRESENTATION);
    }

    @Test
    public void create_기본생성자() throws Exception{
        Piece pawn = new Piece();
        assertEquals(Piece.WHITE_COLOR,pawn.getColor());
        assertEquals(Piece.WHITE_REPRESENTATION,pawn.getRepresentation());
    }
}
