package com.woowatechcamp.chess.pieces;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PieceTest {

    @Test
    @DisplayName("모든 흰색, 검은색 기물이 생성되어야 한다")
    public void create() {
        verifyPiece(Piece.createWhitePawn(), Color.WHITE, Type.WHITE_PAWN.getSymbol());
        verifyPiece(Piece.createBlackPawn(), Color.BLACK, Type.BLACK_PAWN.getSymbol());
        verifyPiece(Piece.createWhiteRook(), Color.WHITE, Type.WHITE_ROOK.getSymbol());
        verifyPiece(Piece.createBlackRook(), Color.BLACK, Type.BLACK_ROOK.getSymbol());
        verifyPiece(Piece.createWhiteKnight(), Color.WHITE, Type.WHITE_KNIGHT.getSymbol());
        verifyPiece(Piece.createBlackKnight(), Color.BLACK, Type.BLACK_KNIGHT.getSymbol());
        verifyPiece(Piece.createWhiteBishop(), Color.WHITE, Type.WHITE_BISHOP.getSymbol());
        verifyPiece(Piece.createBlackBishop(), Color.BLACK, Type.BLACK_BISHOP.getSymbol());
        verifyPiece(Piece.createWhiteQueen(), Color.WHITE, Type.WHITE_QUEEN.getSymbol());
        verifyPiece(Piece.createBlackQueen(), Color.BLACK, Type.BLACK_QUEEN.getSymbol());
        verifyPiece(Piece.createWhiteKing(), Color.WHITE, Type.WHITE_KING.getSymbol());
        verifyPiece(Piece.createBlackKing(), Color.BLACK, Type.BLACK_KING.getSymbol());
    }

    void verifyPiece(final Piece piece, final Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    void 기물이_흰색일_경우_isWhite가_true_이다() {
        Piece piece = Piece.createWhitePawn();
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    void 기물이_검은색일_경우_isBlack가_true_이다() {
        Piece piece = Piece.createBlackPawn();
        assertThat(piece.isBlack()).isTrue();
    }
}
