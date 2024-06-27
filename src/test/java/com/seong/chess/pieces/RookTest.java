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

class RookTest {

    @ParameterizedTest
    @MethodSource("rookMove")
    @DisplayName("룩은 정방향으로 움직일 수 있다.")
    void rookMove(String sourcePosition, Direction direction, int moveCount, String expectedPosition) {
        //given
        Rook rook = Rook.createBlack();

        //when
        Position nextPosition = rook.nextPosition(sourcePosition, direction, moveCount);

        //then
        assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
    }

    private static Stream<Arguments> rookMove() {
        return Stream.of(
                Arguments.arguments("d5", Direction.NORTH, 3, "d8"),
                Arguments.arguments("d5", Direction.SOUTH, 4, "d1"),
                Arguments.arguments("d5", Direction.EAST, 4, "h5"),
                Arguments.arguments("d5", Direction.WEST, 3, "a5")
        );
    }

    @ParameterizedTest
    @MethodSource("rookOnlyMoveRightDirectionButOthersException")
    @DisplayName("룩은 정방향 이외의 방향으로 움직이면 예외가 발생한다.")
    void rookOnlyMoveRightDirectionButOthersException(Direction direction) {
        //given
        Rook rook = Rook.createBlack();
        String sourcePosition = "d4";

        //when
        Exception exception = catchException(() -> rook.nextPosition(sourcePosition, direction, 1));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }


    private static Stream<Arguments> rookOnlyMoveRightDirectionButOthersException() {
        return Arrays.stream(Direction.values())
                .filter(direction -> !direction.isRight())
                .map(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("rookMoveOverChessBoardThenException")
    @DisplayName("체스판 밖으로 이동하려고 시도하면 예외가 발생한다.")
    void rookMoveOverChessBoardThenException(Direction direction) {
        //given
        Rook rook = Rook.createBlack();
        String sourcePosition = "d4";

        //when
        Exception exception = catchException(() -> rook.nextPosition(sourcePosition, direction, 100));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> rookMoveOverChessBoardThenException() {
        return Arrays.stream(Direction.values())
                .filter(Direction::isRight)
                .map(Arguments::arguments);
    }
}
