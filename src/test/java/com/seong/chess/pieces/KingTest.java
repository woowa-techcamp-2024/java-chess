package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.seong.chess.Position;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest
    @MethodSource("kingMove")
    @DisplayName("킹은 모든 방향으로 1칸씩 움직일 수 있다.")
    void kingMove(String sourcePosition, Direction direction, String expectedPosition) {
        //given
        King king = King.createBlack();

        //when
        Position nextPosition = king.nextPosition(sourcePosition, direction, 1);

        //then
        assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
    }

    private static Stream<Arguments> kingMove() {
        return Stream.of(
                Arguments.arguments("e2", Direction.NORTH, "e3"),
                Arguments.arguments("e2", Direction.SOUTH, "e1"),
                Arguments.arguments("e2", Direction.EAST, "f2"),
                Arguments.arguments("e2", Direction.WEST, "d2"),
                Arguments.arguments("e2", Direction.NORTHEAST, "f3"),
                Arguments.arguments("e2", Direction.SOUTHEAST, "f1"),
                Arguments.arguments("e2", Direction.SOUTHWEST, "d1"),
                Arguments.arguments("e2", Direction.NORTHWEST, "d3")
        );
    }

    @ParameterizedTest
    @MethodSource("kingMoveWhenBoardOutThenException")
    @DisplayName("체스판 밖으로 움직이려고 시도하면 예외가 발생한다.")
    void kingMoveWhenBoardOutThenException(Direction direction) {
        //given
        King king = King.createBlack();
        String sourcePosition = "d4";

        //when
        Exception exception = catchException(
                () -> king.nextPosition(sourcePosition, direction, 100));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> kingMoveWhenBoardOutThenException() {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.isRight() || direction.isDiagonal())
                .map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("킹이 정방향, 대각선 이외의 방향으로 움직이려 하면 예외가 발생한다.")
    void kingOnlyMoveRightAndDiagonalDirectionButOthersException(Direction direction) {
        //given
        King king = King.createBlack();
        String sourcePosition = "d4";

        //when
        Exception exception = catchException(() -> king.nextPosition(sourcePosition, direction, 1));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> kingOnlyMoveRightAndDiagonalDirectionButOthersException() {
        return Arrays.stream(Direction.values())
                .filter(direction -> !(direction.isRight() || direction.isDiagonal()))
                .map(Arguments::arguments);
    }
}
