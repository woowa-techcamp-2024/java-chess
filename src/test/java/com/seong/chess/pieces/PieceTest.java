package com.seong.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.seong.chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("색과 이름에 따른 체스말 기물이 생성된다.")
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);

        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말은 구분할 수 있다.")
    public void verifyPieceColor() {
        verifyBlackPiece(Piece.createBlackBishop());
        verifyBlackPiece(Piece.createBlackKnight());
        verifyBlackPiece(Piece.createBlackRook());
        verifyBlackPiece(Piece.createBlackQueen());
        verifyBlackPiece(Piece.createBlackKing());
        verifyBlackPiece(Piece.createBlackPawn());

        verifyWhitePiece(Piece.createWhiteBishop());
        verifyWhitePiece(Piece.createWhiteKing());
        verifyWhitePiece(Piece.createWhiteQueen());
        verifyWhitePiece(Piece.createWhiteRook());
        verifyWhitePiece(Piece.createWhiteKnight());
        verifyWhitePiece(Piece.createWhiteKnight());
    }

    private void verifyBlackPiece(Piece piece) {
        assertTrue(piece.isBlack());
        assertFalse(piece.isWhite());
    }

    private void verifyWhitePiece(Piece piece) {
        assertTrue(piece.isWhite());
        assertFalse(piece.isBlack());
    }
}
