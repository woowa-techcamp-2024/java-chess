package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.seong.chess.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @ParameterizedTest
    @MethodSource("knightMove")
    @DisplayName("나이트는 모든 방향으로 3칸씩 움직일 수 있다.")
    void knightMove(String sourcePosition, Direction direction, String expectedPosition) {
        //given
        Knight knight = Knight.createWhite();

        //when
        Position nextPosition = knight.nextPosition(sourcePosition, direction, 1);

        //then
        assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
    }

    private static Stream<Arguments> knightMove() {
        return Stream.of(
                Arguments.arguments("d4", Direction.NNE, "e6"),
                Arguments.arguments("d4", Direction.EEN, "f5"),
                Arguments.arguments("d4", Direction.EES, "f3"),
                Arguments.arguments("d4", Direction.SSE, "e2"),
                Arguments.arguments("d4", Direction.SSW, "c2"),
                Arguments.arguments("d4", Direction.WWS, "b3"),
                Arguments.arguments("d4", Direction.WWN, "b5"),
                Arguments.arguments("d4", Direction.NNW, "c6")
        );
    }

    @ParameterizedTest
    @MethodSource("knightCanNotMoveRightAndDiagonalDirection")
    @DisplayName("나이트는 정방향, 대각선으로 움직이려고 하면 예외가 발생한다.")
    void knightCanNotMoveRightAndDiagonalDirection(Direction direction) {
        //given
        Knight knight = Knight.createBlack();
        String sourcePosition = "d4";

        //when
        Exception exception = catchException(() -> knight.nextPosition(sourcePosition, direction, 1));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> knightCanNotMoveRightAndDiagonalDirection() {
        return Stream.of(
                Arguments.arguments(Direction.NORTH),
                Arguments.arguments(Direction.SOUTH),
                Arguments.arguments(Direction.EAST),
                Arguments.arguments(Direction.WEST),
                Arguments.arguments(Direction.NORTHEAST),
                Arguments.arguments(Direction.SOUTHEAST),
                Arguments.arguments(Direction.SOUTHWEST),
                Arguments.arguments(Direction.NORTHWEST)
        );
    }

    @ParameterizedTest
    @MethodSource("knightMoveOverChessBoardThenException")
    @DisplayName("체스판 밖으로 움직이려고 시도하면 예외가 발생한다.")
    void knightMoveOverChessBoardThenException(String sourcePosition, Direction direction) {
        //given
        Knight knight = Knight.createBlack();

        //when
        Exception exception = catchException(() -> knight.nextPosition(sourcePosition, direction, 1));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> knightMoveOverChessBoardThenException() {
        return Stream.of(
                Arguments.arguments("d8", Direction.NNE),
                Arguments.arguments("d8", Direction.NNW),
                Arguments.arguments("d8", Direction.EEN),
                Arguments.arguments("d8", Direction.WWN),
                Arguments.arguments("a1", Direction.SSE),
                Arguments.arguments("a1", Direction.SSW),
                Arguments.arguments("a1", Direction.WWS),
                Arguments.arguments("a1", Direction.EES)
        );
    }
}