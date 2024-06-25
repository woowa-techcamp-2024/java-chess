package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {

    @DisplayName("정적 팩토리 메서드를 이용하여 원하는 폰을 생성할 수 있다")
    @Test
    void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);
        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createWhiteRook(), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createBlackRook(), Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);
        verifyPiece(Piece.createWhiteQueen(), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackQueen(), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
    }

    @DisplayName("검정색 폰일 때 isBlack이면 true를 반환한다")
    @Test
    void isBlackWhenBlackPawn() {
        // given
        Piece blackPawn = Piece.createBlackPawn();

        // when
        boolean isBlack = blackPawn.isBlack();

        // then
        assertEquals(true, isBlack);
    }

    @DisplayName("흰색 폰일 때 isBlack이면 true를 반환한다")
    @Test
    void isBlackWhenWhitePawn() {
        // given
        Piece blackPawn = Piece.createWhitePawn();

        // when
        boolean isBlack = blackPawn.isBlack();

        // then
        assertEquals(false, isBlack);
    }

    @DisplayName("검정색 폰일 때 isWhite이면 false를 반환한다")
    @Test
    void isWhiteWhenBlackPawn() {
        // given
        Piece blackPawn = Piece.createBlackPawn();

        // when
        boolean isWhite = blackPawn.isWhite();

        // then
        assertEquals(false, isWhite);
    }

    @DisplayName("흰색 폰일 때 isWhite이면 true를 반환한다")
    @Test
    void isWhiteWhenWhitePawn() {
        // given
        Piece blackPawn = Piece.createWhitePawn();

        // when
        boolean isWhite = blackPawn.isWhite();

        // then
        assertEquals(true, isWhite);
    }


    private void verifyPiece(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

}
