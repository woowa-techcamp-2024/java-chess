package pe.goblin.chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {
    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void createWhitePawnConcrete() {
        verifyPawn(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    void createBlackPawnConcrete() {
        verifyPawn(Piece.createBlackPawn(), Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION);
    }

    void verifyPawn(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}