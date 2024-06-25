package org.example.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("폰 생성시 색을 지정해서 생성한다.")
    public void create() {
        verifyPawn(Color.WHITE, Piece.WHITE_REPRESENTATION);
        verifyPawn(Color.BLACK, Piece.BLACK_REPRESENTATION);
    }

    private void verifyPawn(final Color color, final char representation) {
        Piece piece = new Piece(color, representation);
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    public void create_기본생성자() throws Exception {
        Piece piece = new Piece();
        assertEquals(Color.WHITE, piece.getColor());
        assertEquals(Piece.WHITE_REPRESENTATION, piece.getRepresentation());
    }

}