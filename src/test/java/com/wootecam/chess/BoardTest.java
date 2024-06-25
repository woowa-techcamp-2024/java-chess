package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Pawn;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("체스판 테스트")
class BoardTest {

    private static Board createBoard() {
        return new Board();
    }

    private static Board createBoard(List<Pawn> pawns) {
        Board board = new Board();
        pawns.forEach(board::add);
        return board;
    }

    @Nested
    class 체스판에_말을_추가할_수_있다 {

        @Test
        void 말을_추가할_수_있다() {
            var board = createBoard();
            var pawn = new Pawn();

            assertThatNoException().isThrownBy(() -> board.add(pawn));
        }
    }

    @Nested
    class 체스판에_추가된_말의_개수를_조회할_수_있다 {

        static Stream<Arguments> pawnListForSize() {
            return Stream.of(
                    Arguments.arguments(List.of(new Pawn(Color.WHITE))),
                    Arguments.arguments(List.of(new Pawn(Color.WHITE), new Pawn(Color.BLACK))),
                    Arguments.arguments(List.of(new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.WHITE)))
            );
        }

        @MethodSource("pawnListForSize")
        @ParameterizedTest
        void 말의_개수를_조회할_수_있다(List<Pawn> pawnList) {
            var board = createBoard(pawnList);

            assertThat(board.size()).isEqualTo(pawnList.size());
        }
    }

    @Nested
    class 추가된_순서로_말을_조회할_수_있다 {

        static Stream<Arguments> pawnListForFind() {
            return Stream.of(
                    Arguments.arguments(List.of(new Pawn(Color.WHITE))),
                    Arguments.arguments(List.of(new Pawn(Color.WHITE), new Pawn(Color.BLACK))),
                    Arguments.arguments(List.of(new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.WHITE)))
            );
        }

        @MethodSource("pawnListForFind")
        @ParameterizedTest
        void 말을_조회할_수_있다(List<Pawn> pawnList) {
            var board = createBoard(pawnList);

            for (int i = 0; i < pawnList.size(); ++i) {
                assertThat(board.findPawn(i)).isEqualTo(pawnList.get(i));
            }
        }

        @ValueSource(ints = {-1, 1})
        @ParameterizedTest(name = "{0}번째 말 조회")
        void 유효하지_않은_순서로_말을_조회하면_예외가_발생한다(int index) {
            var board = createBoard(List.of(new Pawn()));

            assertThatThrownBy(() -> board.findPawn(index))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 체스판을_초기화한다 {

        @Test
        void 체스판을_초기화하면_흰색_폰과_검은색_폰이_각각_열의_길이만큼_존재해야_한다() {
            var board = createBoard();

            board.initialize();

            assertThat(board.size()).isEqualTo(ChessBoard.MAX_COL * 2);
        }
    }
}
