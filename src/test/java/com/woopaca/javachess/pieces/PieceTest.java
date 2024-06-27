package com.woopaca.javachess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("기물 테스트")
public class PieceTest {

    @DisplayName("색상을 갖는 기물이 생성되어야 한다.")
    @Test
    void create() {
        verifyPiece(PieceFactory.createWhitePawn(null), Color.WHITE, Type.PAWN.getWhiteRepresentation());
        verifyPiece(PieceFactory.createBlackPawn(null), Color.BLACK, Type.PAWN.getBlackRepresentation());

        verifyPiece(PieceFactory.createWhiteKnight(null), Color.WHITE, Type.KNIGHT.getWhiteRepresentation());
        verifyPiece(PieceFactory.createBlackKnight(null), Color.BLACK, Type.KNIGHT.getBlackRepresentation());

        verifyPiece(PieceFactory.createWhiteRook(null), Color.WHITE, Type.ROOK.getWhiteRepresentation());
        verifyPiece(PieceFactory.createBlackRook(null), Color.BLACK, Type.ROOK.getBlackRepresentation());

        verifyPiece(PieceFactory.createWhiteBishop(null), Color.WHITE, Type.BISHOP.getWhiteRepresentation());
        verifyPiece(PieceFactory.createBlackBishop(null), Color.BLACK, Type.BISHOP.getBlackRepresentation());

        verifyPiece(PieceFactory.createWhiteQueen(null), Color.WHITE, Type.QUEEN.getWhiteRepresentation());
        verifyPiece(PieceFactory.createBlackQueen(null), Color.BLACK, Type.QUEEN.getBlackRepresentation());

        verifyPiece(PieceFactory.createWhiteKing(null), Color.WHITE, Type.KING.getWhiteRepresentation());
        verifyPiece(PieceFactory.createBlackKing(null), Color.BLACK, Type.KING.getBlackRepresentation());
    }

    void verifyPiece(final Piece piece, final Color color, final char representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

    @DisplayName("기물의 색상을 확인할 수 있다.")
    @Test
    void checkColor() {
        Piece whitePawn = PieceFactory.createWhitePawn(null);
        Piece blackPawn = PieceFactory.createBlackPawn(null);
        assertThat(whitePawn.isWhite()).isTrue();
        assertThat(whitePawn.isBlack()).isFalse();
        assertThat(blackPawn.isBlack()).isTrue();
        assertThat(blackPawn.isWhite()).isFalse();
    }

    @DisplayName("각 기물의 표현 문자를 조회할 수 있다.")
    @Test
    void getRepresentationPerPiece() {
        assertThat(Type.PAWN.getWhiteRepresentation()).isEqualTo('♙');
        assertThat(Type.PAWN.getBlackRepresentation()).isEqualTo('♟');
    }

    @DisplayName("색상과 타입을 지정해 기물을 생성할 수 있다.")
    @Test
    void create_piece() {
        verifyPieces(PieceFactory.createWhitePawn(null), PieceFactory.createBlackPawn(null), Type.PAWN);
        verifyPieces(PieceFactory.createWhiteKnight(null), PieceFactory.createBlackKnight(null), Type.KNIGHT);
        verifyPieces(PieceFactory.createWhiteRook(null), PieceFactory.createBlackRook(null), Type.ROOK);
        verifyPieces(PieceFactory.createWhiteBishop(null), PieceFactory.createBlackBishop(null), Type.BISHOP);
        verifyPieces(PieceFactory.createWhiteQueen(null), PieceFactory.createBlackQueen(null), Type.QUEEN);
        verifyPieces(PieceFactory.createWhiteKing(null), PieceFactory.createBlackKing(null), Type.KING);

        Piece blank = PieceFactory.createBlank(null);
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.getType()).isEqualTo(Type.NO_PIECE);
    }

    private void verifyPieces(Piece whitePiece, Piece blackPiece, Type type) {
        assertThat(whitePiece.isWhite()).isTrue();
        assertThat(whitePiece.getType()).isEqualTo(type);

        assertThat(blackPiece.isBlack()).isTrue();
        assertThat(blackPiece.getType()).isEqualTo(type);
    }

}
