package org.example.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("팩토리 메서드를 통해 모든 기물 생성")
    void create_piece() {
        verifyPiece(PieceFactory.createWhitePawn(), Piece.Color.WHITE, "p");
        verifyPiece(PieceFactory.createBlackPawn(), Piece.Color.BLACK, "P");
        verifyPiece(PieceFactory.createWhiteKing(), Piece.Color.WHITE, "k");
        verifyPiece(PieceFactory.createBlackKing(), Piece.Color.BLACK, "K");
        verifyPiece(PieceFactory.createWhiteQueen(), Piece.Color.WHITE, "q");
        verifyPiece(PieceFactory.createBlackQueen(), Piece.Color.BLACK, "Q");
        verifyPiece(PieceFactory.createWhiteBishop(), Piece.Color.WHITE, "b");
        verifyPiece(PieceFactory.createBlackBishop(), Piece.Color.BLACK, "B");
        verifyPiece(PieceFactory.createWhiteKnight(), Piece.Color.WHITE, "n");
        verifyPiece(PieceFactory.createBlackKnight(), Piece.Color.BLACK, "N");
        verifyPiece(PieceFactory.createWhiteRook(), Piece.Color.WHITE, "r");
        verifyPiece(PieceFactory.createBlackRook(), Piece.Color.BLACK, "R");
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final String representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("말의 색깔을 구분할 수 있다")
    void shouldReturnCorrectColor() {
        Piece whitePawn = PieceFactory.createWhitePawn();
        Piece blackPawn = PieceFactory.createBlackPawn();

        assertEquals(true, whitePawn.isWhite());
        assertEquals(false, whitePawn.isBlack());
        assertEquals(true, blackPawn.isBlack());
        assertEquals(false, blackPawn.isWhite());
    }

}