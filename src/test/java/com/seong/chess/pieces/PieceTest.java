package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("색과 이름에 따른 체스말 기물이 생성된다.")
    public void create_piece() {
        verifyPiece(Pawn.createWhite(), Pawn.createBlack(), Pawn.class);
        verifyPiece(Knight.createWhite(), Knight.createBlack(), Knight.class);
        verifyPiece(Rook.createWhite(), Rook.createBlack(), Rook.class);
        verifyPiece(Bishop.createWhite(), Bishop.createBlack(), Bishop.class);
        verifyPiece(Queen.createWhite(), Queen.createBlack(), Queen.class);
        verifyPiece(King.createWhite(), King.createBlack(), King.class);

        Piece blank = Blank.create();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Class<?> clazz) {
        assertTrue(whitePiece.isWhite());
        assertThat(whitePiece).isInstanceOf(clazz);

        assertTrue(blackPiece.isBlack());
        assertThat(blackPiece).isInstanceOf(clazz);
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
