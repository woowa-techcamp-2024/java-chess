package com.wootecam.chess.move;

import static com.wootecam.chess.Fixture.createBoard;
import static com.wootecam.chess.Fixture.createPosition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("기물 이동 테스트")
class PieceMovementManagerTest {
    private Board board;
    private PieceMovementManager movementManager;

    @BeforeEach
    void setUp() {
        board = createBoard();
        movementManager = new PieceMovementManager();
    }

    @Test
    void 폰이_아닌_기물_이동_시_도착_위치에_팀_기물이_있다면_예외가_발생한다() {
        Piece whitePawn = Piece.createWhiteQueen();
        Piece whitePawn2 = Piece.createWhitePawn();
        Position from = new Position("e2");
        Position to = new Position("e4");
        board.add(whitePawn, from);
        board.add(whitePawn2, to);

        assertThatThrownBy(() -> movementManager.move(board, from, to))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    class 폰 {
        @Test
        void 도착_위치에_팀_기물이_있다면_예외가_발생한다() {
            Piece whitePawn = Piece.createWhitePawn();
            Piece whitePawn2 = Piece.createWhitePawn();
            Position from = new Position("e2");
            Position to = new Position("e4");
            board.add(whitePawn, from);
            board.add(whitePawn2, to);

            assertThatThrownBy(() -> movementManager.move(board, from, to))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Nested
        class 겅은색인_경우 {

            @Test
            void 도착_위치에_기물이_없다면_처음_이동_시_두_칸을_이동할_수_있다() {
                Piece pawn = Piece.createBlackPawn();
                Position from = createPosition("e7");
                Position to = createPosition("e5");
                board.add(pawn, from);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }

            @Test
            void 도착_위치에_기물이_없다면_처음_이동_시_한_칸을_이동할_수_있다() {
                Piece pawn = Piece.createBlackPawn();
                Position from = createPosition("e7");
                Position to = createPosition("e6");
                board.add(pawn, from);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }

            @Test
            void 도착_위치에_기물이_없고_처음_이동이_아니라면_앞으로_한_칸을_이동할_수_있다() {
                Piece pawn = Piece.createBlackPawn();
                Position from = createPosition("e7");
                Position to = createPosition("e6");
                board.add(pawn, from);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }

            @Test
            void 대각선_위치에_상대_기물이_있다면_대각선_위치로_이동할_수_있다() {
                Piece pawn = Piece.createBlackPawn();
                Position from = createPosition("e7");
                Position to = createPosition("d6");
                board.add(pawn, from);
                board.add(Piece.createWhitePawn(), to);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }
        }

        @Nested
        class 흰색인_경우 {

            @Test
            void 도착_위치에_기물이_없다면_처음_이동_시_두_칸을_이동할_수_있다() {
                Piece pawn = Piece.createWhitePawn();
                Position from = createPosition("b2");
                Position to = createPosition("b4");
                board.add(pawn, from);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }

            @Test
            void 도착_위치에_기물이_없다면_처음_이동_시_한_칸을_이동할_수_있다() {
                Piece pawn = Piece.createWhitePawn();
                Position from = createPosition("b2");
                Position to = createPosition("b3");
                board.add(pawn, from);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }

            @Test
            void 도착_위치에_기물이_없고_처음_이동이_아니라면_앞으로_한_칸을_이동할_수_있다() {
                Piece pawn = Piece.createWhitePawn();
                Position from = createPosition("b2");
                Position to = createPosition("b3");
                board.add(pawn, from);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }

            @Test
            void 대각선_위치에_상대_기물이_있다면_대각선_위치로_이동할_수_있다() {
                Piece pawn = Piece.createWhitePawn();
                Position from = createPosition("b2");
                Position to = createPosition("c3");
                board.add(pawn, from);
                board.add(Piece.createBlackRook(), to);

                movementManager.move(board, from, to);

                assertThat(board.get(to)).isEqualTo(pawn);
            }
        }
    }
}