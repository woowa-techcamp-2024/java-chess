package com.seong.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("색과 이름에 따른 체스말 기물이 생성된다.")
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);

        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);

        verifyPiece(Piece.createWhiteRook(), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createBlackRook(), Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);

        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);

        verifyPiece(Piece.createWhiteQueen(), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackQueen(), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);

        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
    }

    private void verifyPiece(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말은 구분할 수 있다.")
    public void verifyPieceColor() {
        verifyBlackPiece(Piece.createBlackBishop());
        verifyBlackPiece(Piece.createBlackKnight());
        verifyBlackPiece(Piece.createBlackRook());
        verifyBlackPiece(Piece.createBlackQueen());
        verifyBlackPiece(Piece.createBlackKing());
        verifyBlackPiece(Piece.createBlackPawn());

        verifyWhitePiece(Piece.createWhiteBishop());
        verifyWhitePiece(Piece.createWhiteKing());
        verifyWhitePiece(Piece.createWhiteQueen());
        verifyWhitePiece(Piece.createWhiteRook());
        verifyWhitePiece(Piece.createWhiteKnight());
        verifyWhitePiece(Piece.createWhiteKnight());
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
