package org.example.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("팩토리 메서드를 통해 모든 기물 생성")
    void create_piece() {
        verifyPiece(PieceFactory.createWhitePawn(), Color.WHITE, "p");
        verifyPiece(PieceFactory.createBlackPawn(), Color.BLACK, "P");
        verifyPiece(PieceFactory.createWhiteKing(), Color.WHITE, "k");
        verifyPiece(PieceFactory.createBlackKing(), Color.BLACK, "K");
        verifyPiece(PieceFactory.createWhiteQueen(), Color.WHITE, "q");
        verifyPiece(PieceFactory.createBlackQueen(), Color.BLACK, "Q");
        verifyPiece(PieceFactory.createWhiteBishop(), Color.WHITE, "b");
        verifyPiece(PieceFactory.createBlackBishop(), Color.BLACK, "B");
        verifyPiece(PieceFactory.createWhiteKnight(), Color.WHITE, "n");
        verifyPiece(PieceFactory.createBlackKnight(), Color.BLACK, "N");
        verifyPiece(PieceFactory.createWhiteRook(), Color.WHITE, "r");
        verifyPiece(PieceFactory.createBlackRook(), Color.BLACK, "R");
    }

    private void verifyPiece(final Piece piece, final Color color, final String representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

}