package com.woowatechcamp.chess.pieces;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PieceTest {

    @Test
    @DisplayName("모든 흰색, 검은색 기물이 생성되어야 한다")
    public void create() {
        verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.Type.PAWN.getBlackRepresentation());
        verifyPiece(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.Type.ROOK.getWhiteRepresentation());
        verifyPiece(Piece.createBlackRook(), Piece.Color.BLACK, Piece.Type.ROOK.getBlackRepresentation());
        verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.Type.KNIGHT.getBlackRepresentation());
        verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.Type.BISHOP.getBlackRepresentation());
        verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.Type.QUEEN.getBlackRepresentation());
        verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKing(), Piece.Color.BLACK, Piece.Type.KING.getBlackRepresentation());

        Piece blank = Piece.createBlank();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.getType()).isEqualTo(Piece.Type.BLANK);
    }

    void verifyPiece(final Piece piece, final Piece.Color color, final char representation) {
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
