package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.seong.chess.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

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
    @MethodSource(value = "bishopCanNotMoveRightDirection")
    @DisplayName("비숍이 정방향으로 움직이려고 하면 예외가 발생한다.")
    void bishopCanNotMoveRightDirection(Direction direction) {
        //given
        Bishop black = Bishop.createBlack();
        String sourcePosition = "d4";

        //when
        Exception exception = catchException(() -> black.nextPosition(sourcePosition, direction, 3));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> bishopCanNotMoveRightDirection() {
        return Stream.of(
                Arguments.arguments(Direction.NORTH),
                Arguments.arguments(Direction.SOUTH),
                Arguments.arguments(Direction.WEST),
                Arguments.arguments(Direction.EAST)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "bishopMoveOverChessBoardThenException")
    @DisplayName("체스판 밖으로 움직이려고 시도하면 예외가 발생한다.")
    void bishopMoveOverChessBoardThenException(Direction direction, int moveCount) {
        //given
        Bishop black = Bishop.createBlack();
        String sourcePosition = "d4";

        //when
        Exception exception = catchException(() -> black.nextPosition(sourcePosition, direction, moveCount));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> bishopMoveOverChessBoardThenException() {
        return Stream.of(
                Arguments.arguments(Direction.NORTHEAST, 100),
                Arguments.arguments(Direction.NORTHWEST, 100),
                Arguments.arguments(Direction.SOUTHEAST, 100),
                Arguments.arguments(Direction.SOUTHWEST, 100)
        );
    }
}