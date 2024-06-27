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

class QueenTest {

    @Test
    @DisplayName("퀸은 다음의 위치로 움직일 수 있다.")
    void queenMove() {
        //given
        Queen queen = Queen.createWhite();
        String sourcePosition = "d4";

        //when
        List<Position> positions = queen.findMovablePosition(sourcePosition);

        //then
        assertThat(positions).map(Position::convert)
                .containsExactlyInAnyOrder(
                        "d3", "d2", "d1", "d5", "d6", "d7", "d8",
                        "a4", "b4", "c4", "e4", "f4", "g4", "h4",
                        "a1", "b2", "c3", "e5", "f6", "g7", "h8",
                        "a7", "b6", "c5", "e3", "f2", "g1"
                );
    }

    @Nested
    @DisplayName("퀸의 움직임은 ")
    class QueenMove {
        @ParameterizedTest
        @MethodSource("moveQueen")
        @DisplayName("퀸은 정방향, 대각선으로 이동할 수 있다.")
        void moveQueen(String sourcePosition, Direction direction, int moveCount, String expectedPosition) {
            //given
            Queen queen = Queen.createBlack();

            //when
            Position nextPosition = queen.nextPosition(sourcePosition, direction, moveCount);

            //then
            assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
        }

        private static Stream<Arguments> moveQueen() {
            return Stream.of(
                    Arguments.arguments("d5", Direction.NORTH, 3, "d8"),
                    Arguments.arguments("d5", Direction.SOUTH, 4, "d1"),
                    Arguments.arguments("d5", Direction.EAST, 4, "h5"),
                    Arguments.arguments("d5", Direction.WEST, 3, "a5"),
                    Arguments.arguments("d5", Direction.NORTHEAST, 3, "g8"),
                    Arguments.arguments("d5", Direction.SOUTHEAST, 4, "h1"),
                    Arguments.arguments("d5", Direction.SOUTHWEST, 3, "a2"),
                    Arguments.arguments("d5", Direction.NORTHWEST, 3, "a8")
            );
        }

        @ParameterizedTest
        @MethodSource("queenDirection")
        @DisplayName("체스판 밖으로 움직이려고 시도하면 예외가 발생한다.")
        void queenDirection(Direction direction) {
            //given
            Queen queen = Queen.createBlack();
            String sourcePosition = "d5";

            //when
            Exception exception = catchException(
                    () -> queen.nextPosition(sourcePosition, direction, 100));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        private static Stream<Arguments> queenDirection() {
            return Arrays.stream(Direction.values())
                    .filter(direction -> direction.isRight() || direction.isDiagonal())
                    .map(Arguments::arguments);
        }

        @ParameterizedTest
        @MethodSource("queenOnlyMoveRightAndDiagonalDirection")
        @DisplayName("퀸은 정방향, 대각선 이외의 방향으로 움직이려고 시도하면 예외가 발생한다.")
        void queenOnlyMoveRightAndDiagonalDirection(Direction direction) {
            //given
            Queen queen = Queen.createWhite();
            String sourcePosition = "d4";

            //when
            Exception exception = catchException(() -> queen.nextPosition(sourcePosition, direction, 1));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        private static Stream<Arguments> queenOnlyMoveRightAndDiagonalDirection() {
            return Arrays.stream(Direction.values())
                    .filter(direction -> !(direction.isRight() || direction.isDiagonal()))
                    .map(Arguments::arguments);
        }
    }
}
