package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Piece.Color.WHITE, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Piece.Color.BLACK, Pawn.BLACK_REPRESENTATION);
    }

    void verifyPawn(final Piece.Color color, final char representation) {
        Pawn pawn = color.equals(Piece.Color.WHITE) ? Pawn.createWhite() : Pawn.createBlack();
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }

    @Test
    @DisplayName("기본생성자는 흰색 폰이 생성되어야 한다")
    void create_기본생성자() {
        Pawn pawn = Pawn.createWhite();
        assertEquals(Piece.Color.WHITE, pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }
}