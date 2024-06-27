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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @Nested
    @DisplayName("폰은 다음 위치로 이동할 수 있다.")
    class PawnMoveSourceToTarget {

        @Test
        @DisplayName("초기 상태라면 2칸까지 이동할 수 있다.")
        void pawnMoveAtInitialState() {
            //given
            Pawn pawn = Pawn.createWhite();
            String sourcePosition = "f2";

            //when
            List<Position> positions = pawn.findMovablePosition(sourcePosition);

            //then
            assertThat(positions).map(Position::convert)
                    .containsExactlyInAnyOrder("f4", "f3");
        }

        @Test
        @DisplayName("초기 상태가 아니라면 1칸만 이동가능하다.")
        void pawnIsNotInitialState() {
            //given
            Pawn pawn = Pawn.createWhite();
            String sourcePosition = "f3";

            //when
            List<Position> positions = pawn.findMovablePosition(sourcePosition);

            //then
            assertThat(positions).map(Position::convert)
                    .containsExactlyInAnyOrder("f4");
        }
    }

    @Nested
    @DisplayName("폰의 움직임은 ")
    class PawnMove {

        @ParameterizedTest
        @CsvSource({"a2,1,a3", "a2,2,a4"})
        @DisplayName("초기 상태에서는 1칸 또는 2칸을 움직일 수 있다.")
        void oneOrTwoWhenInitialState(String sourcePosition, int moveCount, String expectedPosition) {
            //given
            Pawn pawn = Pawn.createWhite();

            //when
            Position nextPosition = pawn.nextPosition(sourcePosition, Direction.NORTH, moveCount);

            //then
            assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
        }

        @Test
        @DisplayName("예외(IllegalArgument): 초기 상태가 아니면 2칸 움직일 수 없다.")
        void illegalArgument_WhenMoveTwoButNotInitialState() {
            //given
            Pawn pawn = Pawn.createWhite();

            //when
            Exception exception = catchException(() -> pawn.nextPosition("a3", Direction.NORTH, 2));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @MethodSource("illegalArgument_WhiteOnlyCanMoveToNorth")
        @DisplayName("예외(IllegalArgument): 흰색은 북쪽 외의 방향으로는 움직일 수 없다.")
        void illegalArgument_WhiteOnlyCanMoveToNorth(Direction direction) {
            //given
            Pawn pawn = Pawn.createWhite();

            //when
            Exception exception = catchException(() -> pawn.nextPosition("d4", direction, 1));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        private static Stream<Arguments> illegalArgument_WhiteOnlyCanMoveToNorth() {
            return Arrays.stream(Direction.values())
                    .filter(direction -> direction != Direction.NORTH)
                    .map(Arguments::arguments);
        }

        @ParameterizedTest
        @MethodSource("illegalArgument_WhiteOnlyCanMoveToSouth")
        @DisplayName("예외(IllegalArgument): 검은색은 남쪽 외의 방향으로는 움직일 수 없다.")
        void illegalArgument_WhiteOnlyCanMoveToSouth(Direction direction) {
            //given
            Pawn pawn = Pawn.createBlack();

            //when
            Exception exception = catchException(() -> pawn.nextPosition("d6", direction, 1));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        private static Stream<Arguments> illegalArgument_WhiteOnlyCanMoveToSouth() {
            return Arrays.stream(Direction.values())
                    .filter(direction -> direction != Direction.SOUTH)
                    .map(Arguments::arguments);
        }

        @Test
        @DisplayName("예외(IllegalArgument): 체스판 밖으로 이동할 수 없다.")
        void illegalArgument_WhenPawnMoveOverChessBoard() {
            //given
            Pawn pawn = Pawn.createWhite();

            //when
            Exception exception = catchException(() -> pawn.nextPosition("d8", Direction.NORTH, 1));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }
    }
}