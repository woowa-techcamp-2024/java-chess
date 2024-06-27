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
    @DisplayName("King Tests")
    class KingTest {

        @Test
        @DisplayName("유효한 규칙")
        void testValidMove() {
            King king = new King(Piece.Color.WHITE);

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
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            King king = new King(Piece.Color.WHITE);

            assertThat(king.isValidMove("e2", "e4")).isFalse();
            assertThat(king.isValidMove("e2", "g3")).isFalse();
            assertThat(king.isValidMove("e2", "c1")).isFalse();
            assertThat(king.isValidMove("e2", "e5")).isFalse();
            assertThat(king.isValidMove("e2", "h2")).isFalse();
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            King king = new King(Piece.Color.WHITE);

            assertThatThrownBy(() -> king.isValidMove("e2", "e9"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> king.isValidMove("e2", "i2"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");
        }
    }

    @Nested
    @DisplayName("Pawn Tests")
    class PawnTest {

        @Test
        @DisplayName("유효한 규칙")
        void testValidMove() {
            Pawn pawn = new Pawn(Piece.Color.WHITE);

            assertThat(pawn.isValidMove("e2", "e3")).isTrue(); // One step forward
            assertThat(pawn.isValidMove("e2", "e4")).isTrue(); // Two steps forward from initial position
            assertThat(pawn.isValidMove("e5", "e6")).isTrue(); // One step forward
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Pawn pawn = new Pawn(Piece.Color.WHITE);

            assertThat(pawn.isValidMove("e2", "e5")).isFalse(); // Three steps forward
            assertThat(pawn.isValidMove("e2", "f3")).isFalse(); // Diagonal move without capturing
            assertThat(pawn.isValidMove("e2", "e1")).isFalse(); // Backward move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Pawn pawn = new Pawn(Piece.Color.WHITE);

            assertThatThrownBy(() -> pawn.isValidMove("e2", "e9"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> pawn.isValidMove("e2", "i2"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");
        }
    }

    @Nested
    @DisplayName("Rook Tests")
    class RookTest {

        @Test
        @DisplayName("유효한 규칙")
        void testValidMove() {
            Rook rook = new Rook(Piece.Color.WHITE);

            assertThat(rook.isValidMove("a1", "a8")).isTrue(); // Vertical move
            assertThat(rook.isValidMove("a1", "h1")).isTrue(); // Horizontal move
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Rook rook = new Rook(Piece.Color.WHITE);

            assertThat(rook.isValidMove("a1", "b2")).isFalse(); // Diagonal move
            assertThat(rook.isValidMove("a1", "c3")).isFalse(); // Diagonal move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Rook rook = new Rook(Piece.Color.WHITE);

            assertThatThrownBy(() -> rook.isValidMove("a1", "a9"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> rook.isValidMove("a1", "i1"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");
        }
    }

    @Nested
    @DisplayName("Bishop Tests")
    class BishopTest {

        @Test
        @DisplayName("유효한 규칙")
        void testValidMove() {
            Bishop bishop = new Bishop(Piece.Color.WHITE);

            assertThat(bishop.isValidMove("c1", "a3")).isTrue(); // Diagonal move
            assertThat(bishop.isValidMove("c1", "e3")).isTrue(); // Diagonal move
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Bishop bishop = new Bishop(Piece.Color.WHITE);

            assertThat(bishop.isValidMove("c1", "c2")).isFalse(); // Vertical move
            assertThat(bishop.isValidMove("c1", "d2")).isFalse(); // Horizontal move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Bishop bishop = new Bishop(Piece.Color.WHITE);

            assertThatThrownBy(() -> bishop.isValidMove("c1", "a9"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> bishop.isValidMove("c1", "i3"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");
        }
    }

    @Nested
    @DisplayName("Knight Tests")
    class KnightTest {

        @Test
        @DisplayName("유효한 규칙")
        void testValidMove() {
            Knight knight = new Knight(Piece.Color.WHITE);

            assertThat(knight.isValidMove("b1", "c3")).isTrue(); // L-shaped move
            assertThat(knight.isValidMove("b1", "a3")).isTrue(); // L-shaped move
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Knight knight = new Knight(Piece.Color.WHITE);

            assertThat(knight.isValidMove("b1", "b3")).isFalse(); // Vertical move
            assertThat(knight.isValidMove("b1", "c2")).isFalse(); // Horizontal move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Knight knight = new Knight(Piece.Color.WHITE);

            assertThatThrownBy(() -> knight.isValidMove("b1", "c9"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> knight.isValidMove("b1", "i3"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");
        }
    }

    @Nested
    @DisplayName("Queen Tests")
    class QueenTest {

        @Test
        @DisplayName("유효한 규칙")
        void testValidMove() {
            Queen queen = new Queen(Piece.Color.WHITE);

            assertThat(queen.isValidMove("d1", "d8")).isTrue(); // Vertical move
            assertThat(queen.isValidMove("d1", "h1")).isTrue(); // Horizontal move
            assertThat(queen.isValidMove("d1", "h5")).isTrue(); // Diagonal move
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Queen queen = new Queen(Piece.Color.WHITE);

            assertThat(queen.isValidMove("d1", "e3")).isFalse(); // Invalid diagonal move
            assertThat(queen.isValidMove("d1", "f2")).isFalse(); // Invalid diagonal move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Queen queen = new Queen(Piece.Color.WHITE);

            assertThatThrownBy(() -> queen.isValidMove("d1", "d9"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> queen.isValidMove("d1", "i1"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");
        }
    }

}