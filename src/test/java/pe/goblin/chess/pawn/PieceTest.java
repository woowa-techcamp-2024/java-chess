package pe.goblin.chess.pawn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {
    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void createWhitePawnConcrete() {
        verifyPawn(Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    void createBlackPawnConcrete() {
        verifyPawn(Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION);
    }

    @Test
    public void create_기본생성자() throws Exception {
        Piece piece = new Piece();
        assertEquals(Piece.WHITE_COLOR, piece.getColor());
        assertEquals(Piece.WHITE_REPRESENTATION, piece.getRepresentation());
    }

    void verifyPawn(final String color, final char representation) {
        Piece piece = new Piece(color, representation);
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}