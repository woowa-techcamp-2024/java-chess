package pe.goblin.chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    @DisplayName("흰색 킹이 생성되어야 한다.")
    void createWhiteKing() {
        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 킹이 생성되어야 한다.")
    void createBlackKing() {
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
    }

    @Test
    @DisplayName("흰색 퀸이 생성되어야 한다.")
    void createWhiteQueen() {
        verifyPiece(Piece.createWhiteQueen(), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 퀸이 생성되어야 한다.")
    void createBlackQueen() {
        verifyPiece(Piece.createBlackQueen(), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);
    }

    @Test
    @DisplayName("흰색 룩이 생성되어야 한다.")
    void createWhiteRook() {
        verifyPiece(Piece.createWhiteRook(), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 룩이 생성되어야 한다.")
    void createBlackRook() {
        verifyPiece(Piece.createBlackRook(), Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);
    }

    @Test
    @DisplayName("흰색 비숍이 생성되어야 한다.")
    void createWhiteBishop() {
        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 비숍이 생성되어야 한다.")
    void createBlackBishop() {
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);
    }

    @Test
    @DisplayName("흰색 나이트가 생성되어야 한다.")
    void createWhiteKnight() {
        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 나이트가 생성되어야 한다.")
    void createBlackKnight() {
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void createWhitePawn() {
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    void createBlackPawn() {
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);
    }

    void verifyPiece(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }

    @Nested
    @DisplayName("체스 말의 색깔을 확인할 수 있다.")
    class CheckColor{
        @Test
        @DisplayName("흰색 폰의 색깔을 확인할 수 있다.")
        void checkWhitePawnColor() {
            Piece whitePawn = Piece.createWhitePawn();
            assertTrue(whitePawn.isWhite());
            assertFalse(whitePawn.isBlack());
        }
        @Test
        @DisplayName("검은색 폰의 색깔을 확인할 수 있다.")
        void checkBlackPawnColor() {
            Piece blackPawn = Piece.createBlackPawn();
            assertTrue(blackPawn.isBlack());
            assertFalse(blackPawn.isWhite());
        }
    }
}
