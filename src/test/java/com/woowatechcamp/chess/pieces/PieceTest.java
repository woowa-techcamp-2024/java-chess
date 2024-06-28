package com.woowatechcamp.chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @Test
    @DisplayName("모든 흰색, 검은색 기물이 생성되어야 한다")
    public void create() {
        verifyPiece(PieceFactory.createWhitePawn(new Position("a2")), PieceFactory.createBlackPawn(new Position("a7")), Piece.Type.PAWN);
        verifyPiece(PieceFactory.createWhiteRook(new Position("a1")), PieceFactory.createBlackRook(new Position("a8")), Piece.Type.ROOK);
        verifyPiece(PieceFactory.createWhiteKnight(new Position("b1")), PieceFactory.createBlackKnight(new Position("b8")), Piece.Type.KNIGHT);
        verifyPiece(PieceFactory.createWhiteBishop(new Position("c1")), PieceFactory.createBlackBishop(new Position("c8")), Piece.Type.BISHOP);
        verifyPiece(PieceFactory.createWhiteQueen(new Position("d1")), PieceFactory.createBlackQueen(new Position("d8")), Piece.Type.QUEEN);
        verifyPiece(PieceFactory.createWhiteKing(new Position("e1")), PieceFactory.createBlackKing(new Position("e8")), Piece.Type.KING);

        Piece blank = PieceFactory.createBlank(new Position("a3"));
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
