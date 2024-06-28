package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class PiecesTest {

    @Test
    void 모든_기물을_생성할_수_있다() {
        // when
        Piece blank = new Blank();

        // then
        assertAll(
                () -> verifyPiece(new Pawn(Color.WHITE), Color.WHITE, Type.PAWN),
                () -> verifyPiece(new Pawn(Color.BLACK), Color.BLACK, Type.PAWN),
                () -> verifyPiece(new Knight(Color.WHITE), Color.WHITE, Type.KNIGHT),
                () -> verifyPiece(new Knight(Color.BLACK), Color.BLACK, Type.KNIGHT),
                () -> verifyPiece(new Rook(Color.WHITE), Color.WHITE, Type.ROOK),
                () -> verifyPiece(new Rook(Color.BLACK), Color.BLACK, Type.ROOK),
                () -> verifyPiece(new Bishop(Color.WHITE), Color.WHITE, Type.BISHOP),
                () -> verifyPiece(new Bishop(Color.BLACK), Color.BLACK, Type.BISHOP),
                () -> verifyPiece(new Queen(Color.WHITE), Color.WHITE, Type.QUEEN),
                () -> verifyPiece(new Queen(Color.BLACK), Color.BLACK, Type.QUEEN),
                () -> verifyPiece(new King(Color.WHITE), Color.WHITE, Type.KING),
                () -> verifyPiece(new King(Color.BLACK), Color.BLACK, Type.KING)
        );
    }

    private void verifyPiece(Piece piece, Color color, Type representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation.findRepresentation(color));
    }

    @Test
    void 기물이_존재하지_않는_Piece도_생성할_수_잇다() {
        // when
        Piece blankPiece = new Blank();

        assertAll(
                () -> assertThat(blankPiece.isWhite()).isFalse(),
                () -> assertThat(blankPiece.isBlack()).isFalse(),
                () -> assertThat(blankPiece.getType()).isEqualTo(Type.NO_PIECE)
        );
    }

    @Test
    void isWhite는_기물이_흰색인지에_대한_boolean_값을_반환한다() {
        // given
        Piece whitePawn = new Pawn(Color.WHITE);
        Piece blackPawn = new Pawn(Color.BLACK);

        // then
        assertAll(
                () -> assertThat(whitePawn.isWhite()).isTrue(),
                () -> assertThat(blackPawn.isWhite()).isFalse()
        );
    }

    @Test
    void isBlack은_기물이_검정색인지에_대한_boolean_값을_반환한다() {
        // given
        Piece whitePawn = new Pawn(Color.WHITE);
        Piece blackPawn = new Pawn(Color.BLACK);

        // when
        assertAll(
                () -> assertThat(whitePawn.isBlack()).isFalse(),
                () -> assertThat(blackPawn.isBlack()).isTrue()
        );
    }

    @Test
    void 동일한_색상과_타입이면_참을_반환한다() {
        // given
        Piece whitePawn = new Pawn(Color.WHITE);

        // when
        boolean isSameColorAndType = whitePawn.isSameColorAndType(Color.WHITE, Type.PAWN);

        // then
        assertThat(isSameColorAndType).isTrue();
    }

    @Test
    void 동일한_색상을_가진_폰을_제외한_기물이라면_참을_반환한다() {
        // given
        Piece piece = new Rook(Color.BLACK);

        // when
        boolean isApplicablePiece = piece.isApplicablePiece(Color.BLACK);

        // then
        assertThat(isApplicablePiece).isTrue();
    }
}
