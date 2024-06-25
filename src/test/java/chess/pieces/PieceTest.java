package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.pieces.Piece.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {

    @DisplayName("팩토리 메소드를 테스트한다.")
    @ParameterizedTest
    @MethodSource
    void create_piece(final Piece piece, final Color color, final char representation) {
        verifyPiece(piece, color, representation);
    }

    private static Stream<Arguments> create_piece() {
        return Stream.of(
                Arguments.of(Piece.createWhitePawn(), Color.WHITE, Piece.WHITE_PAWN_REPRESENTATION),
                Arguments.of(Piece.createBlackPawn(), Color.BLACK, Piece.BLACK_PAWN_REPRESENTATION),
                Arguments.of(Piece.createWhiteKnight(), Color.WHITE, Piece.WHITE_KNIGHT_REPRESENTATION),
                Arguments.of(Piece.createBlackKnight(), Color.BLACK, Piece.BLACK_KNIGHT_REPRESENTATION),
                Arguments.of(Piece.createWhiteRook(), Color.WHITE, Piece.WHITE_ROOK_REPRESENTATION),
                Arguments.of(Piece.createBlackRook(), Color.BLACK, Piece.BLACK_ROOK_REPRESENTATION),
                Arguments.of(Piece.createWhiteBishop(), Color.WHITE, Piece.WHITE_BISHOP_REPRESENTATION),
                Arguments.of(Piece.createBlackBishop(), Color.BLACK, Piece.BLACK_BISHOP_REPRESENTATION),
                Arguments.of(Piece.createWhiteQueen(), Color.WHITE, Piece.WHITE_QUEEN_REPRESENTATION),
                Arguments.of(Piece.createBlackQueen(), Color.BLACK, Piece.BLACK_QUEEN_REPRESENTATION),
                Arguments.of(Piece.createWhiteKing(), Color.WHITE, Piece.WHITE_KING_REPRESENTATION),
                Arguments.of(Piece.createBlackKing(), Color.BLACK, Piece.BLACK_KING_REPRESENTATION)
        );
    }

    private void verifyPiece(final Piece piece, final Color color, final char representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

    @DisplayName("검은색 말과 흰색 말을 구분한다")
    @Test
    void checkColor() {
        Piece blackPawn = Piece.createBlackPawn();
        Piece whiteQueen = Piece.createWhiteQueen();
        assertTrue(blackPawn.isBlack());
        assertFalse(blackPawn.isWhite());
        assertTrue(whiteQueen.isWhite());
        assertFalse(whiteQueen.isBlack());
    }
}