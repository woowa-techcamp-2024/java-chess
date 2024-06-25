package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class PiecesTest {

    @Test
    void 모든_기물을_생성할_수_있다() {
        // when
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();

        Piece whiteKnight = Piece.createWhiteKnight();
        Piece blackKnight = Piece.createBlackKnight();

        Piece whiteRook = Piece.createWhiteRook();
        Piece blackRook = Piece.createBlackRook();

        Piece whiteBishop = Piece.createWhiteBishop();
        Piece blackBishop = Piece.createBlackBishop();

        Piece whiteQueen = Piece.createWhiteQueen();
        Piece blackQueen = Piece.createBlackQueen();

        Piece whiteKing = Piece.createWhiteKing();
        Piece blackKing = Piece.createBlackKing();

        // then
        assertAll(
                () -> verifyPiece(whitePawn, Color.WHITE, Type.PAWN),
                () -> verifyPiece(blackPawn, Color.BLACK, Type.PAWN),
                () -> verifyPiece(whiteKnight, Color.WHITE, Type.KNIGHT),
                () -> verifyPiece(blackKnight, Color.BLACK, Type.KNIGHT),
                () -> verifyPiece(whiteRook, Color.WHITE, Type.ROOK),
                () -> verifyPiece(blackRook, Color.BLACK, Type.ROOK),
                () -> verifyPiece(whiteBishop, Color.WHITE, Type.BISHOP),
                () -> verifyPiece(blackBishop, Color.BLACK, Type.BISHOP),
                () -> verifyPiece(whiteQueen, Color.WHITE, Type.QUEEN),
                () -> verifyPiece(blackQueen, Color.BLACK, Type.QUEEN),
                () -> verifyPiece(whiteKing, Color.WHITE, Type.KING),
                () -> verifyPiece(blackKing, Color.BLACK, Type.KING)
        );
    }

    private void verifyPiece(Piece piece, Color color, Type representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation.findRepresentation(color));
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
