package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.pieces.Piece.Type;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {

    @DisplayName("색과 기물에 따른 팩토리 메소드를 테스트한다.")
    @ParameterizedTest
    @MethodSource
    void create_piece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        verifyPiece(whitePiece, blackPiece, type);
    }

    private void verifyPiece(Piece whitePiece, Piece blackPiece, Type type) {
        assertTrue(whitePiece.isWhite());
        assertFalse(whitePiece.isBlack());
        assertEquals(whitePiece.getType(), type);

        assertTrue(blackPiece.isBlack());
        assertFalse(blackPiece.isWhite());
        assertEquals(blackPiece.getType(), type);
    }

    private static Stream<Arguments> create_piece() {
        return Stream.of(
                Arguments.of(PieceFactory.createWhitePawn(), PieceFactory.createBlackPawn(), Type.PAWN),
                Arguments.of(PieceFactory.createWhiteKnight(), PieceFactory.createBlackKnight(), Type.KNIGHT),
                Arguments.of(PieceFactory.createWhiteBishop(), PieceFactory.createBlackBishop(), Type.BISHOP),
                Arguments.of(PieceFactory.createWhiteRook(), PieceFactory.createBlackRook(), Type.ROOK),
                Arguments.of(PieceFactory.createWhiteQueen(), PieceFactory.createBlackQueen(), Type.QUEEN),
                Arguments.of(PieceFactory.createWhiteKing(), PieceFactory.createBlackKing(), Type.KING)
        );
    }

    @DisplayName("기물이 없는 공간을 나타내는 Blank 피스를 테스트한다.")
    @Test
    void create_blank() {
        Piece blank = PieceFactory.createBlankPiece();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }


    @DisplayName("검은색 말과 흰색 말을 구분한다")
    @Test
    void checkColor() {
        Piece blackPawn = PieceFactory.createBlackPawn();
        Piece whiteQueen = PieceFactory.createWhitePawn();
        assertTrue(blackPawn.isBlack());
        assertFalse(blackPawn.isWhite());
        assertTrue(whiteQueen.isWhite());
        assertFalse(whiteQueen.isBlack());
    }
}
