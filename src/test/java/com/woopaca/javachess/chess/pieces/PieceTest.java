package com.woopaca.javachess.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰 기물 테스트")
public class PieceTest {

    @DisplayName("색상을 갖는 기물이 생성되어야 한다.")
    @Test
    void create() {
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, PieceType.PAWN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, PieceType.PAWN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, PieceType.KNIGHT.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, PieceType.KNIGHT.getBlackRepresentation());

        verifyPiece(Piece.createWhiteRook(), Piece.WHITE_COLOR, PieceType.ROOK.getWhiteRepresentation());
        verifyPiece(Piece.createBlackRook(), Piece.BLACK_COLOR, PieceType.ROOK.getBlackRepresentation());

        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, PieceType.BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, PieceType.BISHOP.getBlackRepresentation());

        verifyPiece(Piece.createWhiteQueen(), Piece.WHITE_COLOR, PieceType.QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.createBlackQueen(), Piece.BLACK_COLOR, PieceType.QUEEN.getBlackRepresentation());

        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, PieceType.KING.getWhiteRepresentation());
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, PieceType.KING.getBlackRepresentation());
    }

    void verifyPiece(final Piece piece, final String color, final char representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

}
