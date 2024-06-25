package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.pieces.Piece.Type;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("기물의 색상에 따라 다른 representation을 반환한다")
    void getRepresentationPerPiece()  {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());
    }

    @DisplayName("정적 팩토리 메서드를 이용하여 원하는 폰을 생성할 수 있다")
    @Test
    void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);

        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());

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

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

}
