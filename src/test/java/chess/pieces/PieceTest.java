package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {

    @Test
    @DisplayName("기본 생성자를 이용하면 흰색 폰이 생성되고, 기본 표현이 설정되어야 한다")
    void create_기본생성자() throws Exception {
        Piece piece = new Piece();
        assertEquals(Piece.WHITE_COLOR, piece.getColor());
        assertEquals(Piece.WHITE_REPRESENTATION, piece.getRepresentation());
    }

    @Test
    @DisplayName("생성자를 이용하면 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION);
        verifyPawn(Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION);
    }

    private void verifyPawn(final String color, final char representation) {
        Piece piece = new Piece(color, representation);
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}
