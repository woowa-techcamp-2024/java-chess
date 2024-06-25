package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class PiecesTest {

    @Test
    void 모든_기물을_생성할_수_있다() {
        // when
        Piece blank = Piece.createBlank();

        // then
        assertAll(
                () -> verifyPiece(Piece.createWhite(Type.PAWN), Color.WHITE, Type.PAWN),
                () -> verifyPiece(Piece.createBlack(Type.PAWN), Color.BLACK, Type.PAWN),
                () -> verifyPiece(Piece.createWhite(Type.KNIGHT), Color.WHITE, Type.KNIGHT),
                () -> verifyPiece(Piece.createBlack(Type.KNIGHT), Color.BLACK, Type.KNIGHT),
                () -> verifyPiece(Piece.createWhite(Type.ROOK), Color.WHITE, Type.ROOK),
                () -> verifyPiece(Piece.createBlack(Type.ROOK), Color.BLACK, Type.ROOK),
                () -> verifyPiece(Piece.createWhite(Type.BISHOP), Color.WHITE, Type.BISHOP),
                () -> verifyPiece(Piece.createBlack(Type.BISHOP), Color.BLACK, Type.BISHOP),
                () -> verifyPiece(Piece.createWhite(Type.QUEEN), Color.WHITE, Type.QUEEN),
                () -> verifyPiece(Piece.createBlack(Type.QUEEN), Color.BLACK, Type.QUEEN),
                () -> verifyPiece(Piece.createWhite(Type.KING), Color.WHITE, Type.KING),
                () -> verifyPiece(Piece.createBlack(Type.KING), Color.BLACK, Type.KING)
        );
    }

    private void verifyPiece(Piece piece, Color color, Type representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation.findRepresentation(color));
    }

    @Test
    void 기물이_존재하지_않는_Piece도_생성할_수_잇다() {
        // when
        Piece blankPiece = Piece.createBlank();

        assertAll(
                () -> assertThat(blankPiece.isWhite()).isFalse(),
                () -> assertThat(blankPiece.isBlack()).isFalse(),
                () -> assertThat(blankPiece.getType()).isEqualTo(Type.NO_PIECE)
        );
    }

    @Test
    void isWhite는_기물이_흰색인지에_대한_boolean_값을_반환한다() {
        // given
        Piece whitePawn = new Piece(Color.WHITE, Type.PAWN);
        Piece blackPawn = new Piece(Color.BLACK, Type.PAWN);

        // when
        assertAll(
                () -> assertThat(whitePawn.isWhite()).isTrue(),
                () -> assertThat(blackPawn.isWhite()).isFalse()
        );
    }

    @Test
    void isBlack은_기물이_검정색인지에_대한_boolean_값을_반환한다() {
        // given
        Piece whitePawn = new Piece(Color.WHITE, Type.PAWN);
        Piece blackPawn = new Piece(Color.BLACK, Type.PAWN);

        // when
        assertAll(
                () -> assertThat(whitePawn.isBlack()).isFalse(),
                () -> assertThat(blackPawn.isBlack()).isTrue()
        );
    }
}
