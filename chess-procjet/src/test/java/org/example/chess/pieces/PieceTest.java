package org.example.chess.pieces;

import static org.example.chess.pieces.Piece.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
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

            assertThat(king.isValidMove("e2", "e3", List.of())).isTrue();
            assertThat(king.isValidMove("e2", "d3", List.of())).isTrue();
            assertThat(king.isValidMove("e2", "f3", List.of())).isTrue();
            assertThat(king.isValidMove("e2", "d2", List.of())).isTrue();
            assertThat(king.isValidMove("e2", "f2", List.of())).isTrue();
            assertThat(king.isValidMove("e2", "e1", List.of())).isTrue();
            assertThat(king.isValidMove("e2", "d1", List.of())).isTrue();
            assertThat(king.isValidMove("e2", "f1", List.of())).isTrue();
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            King king = new King(Piece.Color.WHITE);

            king.isValidMove("e2", "e4", List.of());

            assertThat(king.isValidMove("e2", "e4", List.of())).isFalse();
            assertThat(king.isValidMove("e2", "g3", List.of())).isFalse();
            assertThat(king.isValidMove("e2", "c1", List.of())).isFalse();
            assertThat(king.isValidMove("e2", "e5", List.of())).isFalse();
            assertThat(king.isValidMove("e2", "h2", List.of())).isFalse();
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            King king = new King(Piece.Color.WHITE);

            assertThatThrownBy(() -> king.isValidMove("e2", "e9", List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> king.isValidMove("e2", "i2", List.of()))
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

            assertThat(pawn.isValidMove("e2", "e3", List.of())).isTrue();
            assertThat(pawn.isValidMove("e2", "e4", List.of())).isTrue();
            assertThat(pawn.isValidMove("e5", "e6", List.of())).isTrue();
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Pawn pawn = new Pawn(Piece.Color.WHITE);

            assertThat(pawn.isValidMove("e2", "e5", List.of())).isFalse();
            //TODO: 만약 상대방 말이 있다면 이동 가능 현재 미구현 초기 리스트에 대각 방향을 제외시키고 해당 대각에 상대가 있다면 추가해주는 형태로 구현할지도
//            assertThat(pawn.isValidMove("e2", "f3")).isFalse();
            assertThat(pawn.isValidMove("e2", "e1", List.of())).isFalse();
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Pawn pawn = new Pawn(Piece.Color.WHITE);

            assertThatThrownBy(() -> pawn.isValidMove("e2", "e9", List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> pawn.isValidMove("e2", "i2", List.of()))
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

            assertThat(rook.isValidMove("a1", "a8", List.of())).isTrue(); // Vertical move
            assertThat(rook.isValidMove("a1", "h1", List.of())).isTrue(); // Horizontal move
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Rook rook = new Rook(Piece.Color.WHITE);

            assertThat(rook.isValidMove("a1", "b2", List.of())).isFalse(); // Diagonal move
            assertThat(rook.isValidMove("a1", "c3", List.of())).isFalse(); // Diagonal move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Rook rook = new Rook(Piece.Color.WHITE);

            assertThatThrownBy(() -> rook.isValidMove("a1", "a9", List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> rook.isValidMove("a1", "i1", List.of()))
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

            assertThat(bishop.isValidMove("c1", "a3", List.of())).isTrue();
            assertThat(bishop.isValidMove("c1", "e3", List.of())).isTrue();
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Bishop bishop = new Bishop(Piece.Color.WHITE);

            assertThat(bishop.isValidMove("c1", "c2", List.of())).isFalse();
            assertThat(bishop.isValidMove("c1", "d1", List.of())).isFalse();
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Bishop bishop = new Bishop(Piece.Color.WHITE);

            assertThatThrownBy(() -> bishop.isValidMove("c1", "a9", List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> bishop.isValidMove("c1", "i3", List.of()))
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

            assertThat(knight.isValidMove("b1", "c3", List.of())).isTrue();
            assertThat(knight.isValidMove("b1", "a3", List.of())).isTrue();
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Knight knight = new Knight(Piece.Color.WHITE);

            assertThat(knight.isValidMove("b1", "b3", List.of())).isFalse(); // Vertical move
            assertThat(knight.isValidMove("b1", "c2", List.of())).isFalse(); // Horizontal move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Knight knight = new Knight(Piece.Color.WHITE);

            assertThatThrownBy(() -> knight.isValidMove("b1", "c9", List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> knight.isValidMove("b1", "i3", List.of()))
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

            assertThat(queen.isValidMove("d1", "d8", List.of())).isTrue(); // Vertical move
            assertThat(queen.isValidMove("d1", "h1", List.of())).isTrue(); // Horizontal move
            assertThat(queen.isValidMove("d1", "h5", List.of())).isTrue(); // Diagonal move
        }

        @Test
        @DisplayName("유효하지 않은 규칙")
        void testInvalidMove() {
            Queen queen = new Queen(Piece.Color.WHITE);

            assertThat(queen.isValidMove("d1", "e3", List.of())).isFalse(); // Invalid diagonal move
            assertThat(queen.isValidMove("d1", "f2", List.of())).isFalse(); // Invalid diagonal move
        }

        @Test
        @DisplayName("범위를 벗어난 이동")
        void testOutOfBoundsMove() {
            Queen queen = new Queen(Piece.Color.WHITE);

            assertThatThrownBy(() -> queen.isValidMove("d1", "d9", List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");

            assertThatThrownBy(() -> queen.isValidMove("d1", "i1", List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("올바른 위치를 입력해주세요");
        }
    }

}