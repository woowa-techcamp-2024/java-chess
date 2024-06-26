package com.woowatechcamp.chess.pieces;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @Test
    @DisplayName("모든 흰색, 검은색 기물이 생성되어야 한다")
    public void create() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Piece.Type.PAWN);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Piece.Type.ROOK);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Piece.Type.KNIGHT);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Piece.Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Piece.Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Piece.Type.KING);

        Piece blank = Piece.createBlank();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.getType()).isEqualTo(Piece.Type.BLANK);
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
        assertThat(whitePiece.isWhite()).isTrue();
        assertThat(type).isEqualTo(whitePiece.getType());

        assertThat(blackPiece.isBlack()).isTrue();
        assertThat(type).isEqualTo(blackPiece.getType());
    }
}
