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
        verifyPiece(Pawn.createWhite(), Pawn.createBlack(), Type.PAWN);
        verifyPiece(Knight.createWhite(), Knight.createBlack(), Type.KNIGHT);
        verifyPiece(Rook.createWhite(), Rook.createBlack(), Type.ROOK);
        verifyPiece(Bishop.createWhite(), Bishop.createBlack(), Type.BISHOP);
        verifyPiece(Queen.createWhite(), Queen.createBlack(), Type.QUEEN);
        verifyPiece(King.createWhite(), King.createBlack(), Type.KING);

        Piece blank = Blank.create();
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
        verifyBlackPiece(Bishop.createBlack());
        verifyBlackPiece(Knight.createBlack());
        verifyBlackPiece(Rook.createBlack());
        verifyBlackPiece(Queen.createBlack());
        verifyBlackPiece(King.createBlack());
        verifyBlackPiece(Pawn.createBlack());

        verifyWhitePiece(Bishop.createWhite());
        verifyWhitePiece(King.createWhite());
        verifyWhitePiece(Queen.createWhite());
        verifyWhitePiece(Rook.createWhite());
        verifyWhitePiece(Knight.createWhite());
        verifyWhitePiece(Knight.createWhite());
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
