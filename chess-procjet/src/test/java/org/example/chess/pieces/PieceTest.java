package org.example.chess.pieces;

import static org.example.chess.pieces.Piece.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.assertj.core.api.Assertions.*;

import org.example.chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("팩토리 메서드를 통해 모든 기물 생성")
    void create_piece() {
        verifyPiece(PieceFactory.createWhitePawn(), Color.WHITE, Type.PAWN, "p");
        verifyPiece(PieceFactory.createBlackPawn(), Color.BLACK, Type.PAWN, "P");
        verifyPiece(PieceFactory.createWhiteKing(), Color.WHITE, Type.KING, "k");
        verifyPiece(PieceFactory.createBlackKing(), Color.BLACK, Type.KING, "K");
        verifyPiece(PieceFactory.createWhiteQueen(), Color.WHITE, Type.QUEEN, "q");
        verifyPiece(PieceFactory.createBlackQueen(), Color.BLACK, Type.QUEEN, "Q");
        verifyPiece(PieceFactory.createWhiteBishop(), Color.WHITE, Type.BISHOP, "b");
        verifyPiece(PieceFactory.createBlackBishop(), Color.BLACK, Type.BISHOP, "B");
        verifyPiece(PieceFactory.createWhiteKnight(), Color.WHITE, Type.KNIGHT, "n");
        verifyPiece(PieceFactory.createBlackKnight(), Color.BLACK, Type.KNIGHT, "N");
        verifyPiece(PieceFactory.createWhiteRook(), Color.WHITE, Type.ROOK, "r");
        verifyPiece(PieceFactory.createBlackRook(), Color.BLACK, Type.ROOK, "R");
        verifyPiece(PieceFactory.createBlank(), Color.NONCOLOR, Type.NO_TYPE, ".");

        Piece blank = PieceFactory.createBlank();
        assertFalse(blank.isBlack());
        assertFalse(blank.isWhite());
        assertEquals(Type.NO_TYPE, blank.getType());
    }

    private void verifyPiece(final Piece piece, final Color color, final Type type, final String representation) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
        assertEquals(representation, piece.getRepresentation());
    }

    @Test
    @DisplayName("말의 색깔을 구분할 수 있다")
    void shouldReturnCorrectColor() {
        Piece whitePawn = PieceFactory.createWhitePawn();
        Piece blackPawn = PieceFactory.createBlackPawn();

        assertEquals(true, whitePawn.isWhite());
        assertEquals(false, whitePawn.isBlack());
        assertEquals(true, blackPawn.isBlack());
        assertEquals(false, blackPawn.isWhite());
    }

    @Nested
    @DisplayName("King 이동규칙 테스트")
    class KingTest {

        @Test
        void 올바른_규칙의_이동() {
            King king = new King(Color.WHITE);
            assertThat(king.isValidMove("e2", "e3")).isTrue();
            assertThat(king.isValidMove("e2", "d3")).isTrue();
            assertThat(king.isValidMove("e2", "f3")).isTrue();
            assertThat(king.isValidMove("e2", "d2")).isTrue();
            assertThat(king.isValidMove("e2", "f2")).isTrue();
            assertThat(king.isValidMove("e2", "e1")).isTrue();
            assertThat(king.isValidMove("e2", "d1")).isTrue();
            assertThat(king.isValidMove("e2", "f1")).isTrue();
        }

        @Test
        void 올바르지_않은_규칙_이동() {
            King king = new King(Piece.Color.WHITE);

            assertThat(king.isValidMove("e2", "e4")).isFalse();
            assertThat(king.isValidMove("e2", "g3")).isFalse();
            assertThat(king.isValidMove("e2", "c1")).isFalse();
            assertThat(king.isValidMove("e2", "e5")).isFalse();
            assertThat(king.isValidMove("e2", "h2")).isFalse();
        }
    }

}