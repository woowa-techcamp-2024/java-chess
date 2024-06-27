package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.seong.chess.Position;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @Test
    @DisplayName("비숍은 다음 위치로 움직일 수 있다.")
    void bishopMove() {
        //given
        Bishop bishop = Bishop.createBlack();
        String sourcePosition = "d5";

        //when
        List<Position> positions = bishop.findMovablePosition(sourcePosition);

        //then
        assertThat(positions).map(Position::convert)
                .containsExactlyInAnyOrder(
                        "a2", "b3", "c4", "e4", "f3", "g2", "h1",
                        "c6", "b7", "a8", "e6", "f7", "g8"
                );
    }

    @Nested
    @DisplayName("비숍의 움직임은 ")
    class BishopMove {
        @ParameterizedTest
        @MethodSource(value = "bishopMove")
        @DisplayName("비숍은 대각선 방향으로 움직일 수 있다.")
        void bishopMove(String sourcePosition, Direction direction, int moveCount, String expectedPosition) {
            //given
            Bishop black = Bishop.createBlack();

            //when
            Position nextPosition = black.nextPosition(sourcePosition, direction, moveCount);

            //then
            assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
        }

        private static Stream<Arguments> bishopMove() {
            return Stream.of(
                    Arguments.arguments("d5", Direction.NORTHEAST, 3, "g8"),
                    Arguments.arguments("d5", Direction.NORTHWEST, 3, "a8"),
                    Arguments.arguments("d5", Direction.SOUTHWEST, 3, "a2"),
                    Arguments.arguments("d5", Direction.SOUTHEAST, 4, "h1")
            );
        }

        @ParameterizedTest
        @MethodSource(value = "bishopOnlyMoveDiagonalDirection")
        @DisplayName("비숍이 정방향으로 움직이려고 하면 예외가 발생한다.")
        void bishopOnlyMoveDiagonalDirection(Direction direction) {
            //given
            Bishop black = Bishop.createBlack();
            String sourcePosition = "d4";

            //when
            Exception exception = catchException(() -> black.nextPosition(sourcePosition, direction, 3));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        private static Stream<Arguments> bishopOnlyMoveDiagonalDirection() {
            return Arrays.stream(Direction.values())
                    .filter(direction -> !direction.isDiagonal())
                    .map(Arguments::arguments);
        }

        @ParameterizedTest
        @MethodSource(value = "bishopMoveOverChessBoardThenException")
        @DisplayName("체스판 밖으로 움직이려고 시도하면 예외가 발생한다.")
        void bishopMoveOverChessBoardThenException(Direction direction) {
            //given
            Bishop black = Bishop.createBlack();
            String sourcePosition = "d4";

            //when
            Exception exception = catchException(() -> black.nextPosition(sourcePosition, direction, 100));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        private static Stream<Arguments> bishopMoveOverChessBoardThenException() {
            return Arrays.stream(Direction.values())
                    .filter(Direction::isDiagonal)
                    .map(Arguments::arguments);
        }
    }
}
