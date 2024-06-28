package com.wootecam.chess.move;

import static com.wootecam.chess.Fixture.createBoard;
import static com.wootecam.chess.Fixture.createPosition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.BoardInitializer;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Nested
    class 폰이_아닌_기물 {

        @Test
        void 이동_시_도착_위치에_팀_기물이_있다면_예외가_발생한다() {
            Piece whitePawn = Piece.createWhiteQueen();
            Piece whitePawn2 = Piece.createWhitePawn();
            Position from = new Position("e2");
            Position to = new Position("e4");
            board.add(whitePawn, from);
            board.add(whitePawn2, to);

            assertThatThrownBy(() -> movementManager.move(board, from, to))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 나이트는_경로_중_기물이_있어도_이동할_수_있다() {
            Piece knight = Piece.createBlackKnight();
            Piece pawn = Piece.createBlackPawn();
            Position from = createPosition("c5");
            Position via = createPosition("d4");
            Position to = createPosition("d3");
            board.add(knight, from);
            board.add(pawn, via);

            movementManager.move(board, from, to);

            assertThat(board.get(to)).isEqualTo(knight);
        }

        @CsvSource({
                "a8, a1",
                "h8, h1",
                "c8, a6",
                "f8, h6",
                "e8, g6",
        })
        @ParameterizedTest
        void 나이트를_제외한_기물은_경로_중_기물이_있으면_이동_시_예외가_발생한다(String fromStr, String toStr) {
            BoardInitializer boardInitializer = new BoardInitializer();
            boardInitializer.initialize(board);

            Position from = createPosition(fromStr);
            Position to = createPosition(toStr);

            assertThatThrownBy(() -> movementManager.move(board, from, to))
                    .isInstanceOf(IllegalArgumentException.class);
        }
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