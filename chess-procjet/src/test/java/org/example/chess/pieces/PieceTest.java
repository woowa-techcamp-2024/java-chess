package org.example.chess.pieces;

import static org.example.chess.pieces.Piece.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.example.chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("팩토리 메서드를 통해 모든 기물 생성")
    void create_piece() {
        verifyPiece(Piece.PieceFactory.createWhitePawn(), Color.WHITE, Type.PAWN, "p");
        verifyPiece(Piece.PieceFactory.createBlackPawn(), Color.BLACK, Type.PAWN, "P");
        verifyPiece(Piece.PieceFactory.createWhiteKing(), Color.WHITE, Type.KING, "k");
        verifyPiece(Piece.PieceFactory.createBlackKing(), Color.BLACK, Type.KING, "K");
        verifyPiece(Piece.PieceFactory.createWhiteQueen(), Color.WHITE, Type.QUEEN, "q");
        verifyPiece(Piece.PieceFactory.createBlackQueen(), Color.BLACK, Type.QUEEN, "Q");
        verifyPiece(Piece.PieceFactory.createWhiteBishop(), Color.WHITE, Type.BISHOP, "b");
        verifyPiece(Piece.PieceFactory.createBlackBishop(), Color.BLACK, Type.BISHOP, "B");
        verifyPiece(Piece.PieceFactory.createWhiteKnight(), Color.WHITE, Type.KNIGHT, "n");
        verifyPiece(Piece.PieceFactory.createBlackKnight(), Color.BLACK, Type.KNIGHT, "N");
        verifyPiece(Piece.PieceFactory.createWhiteRook(), Color.WHITE, Type.ROOK, "r");
        verifyPiece(Piece.PieceFactory.createBlackRook(), Color.BLACK, Type.ROOK, "R");

        Piece blank = Piece.PieceFactory.createBlank();
        assertFalse(blank.isBlack());
        assertFalse(blank.isWhite());
        assertEquals(Type.NO_TYPE, blank.getType());
    }

    private void verifyPiece(final Piece piece, final Color color, final Type type, final String representation) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("말의 색깔을 구분할 수 있다")
    void shouldReturnCorrectColor() {
        Piece whitePawn = Piece.PieceFactory.createWhitePawn();
        Piece blackPawn = Piece.PieceFactory.createBlackPawn();

        assertEquals(true, whitePawn.isWhite());
        assertEquals(false, whitePawn.isBlack());
        assertEquals(true, blackPawn.isBlack());
        assertEquals(false, blackPawn.isWhite());
    }

}