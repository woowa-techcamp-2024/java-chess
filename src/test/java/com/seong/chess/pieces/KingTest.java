package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.seong.chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource({
            "e2,NORTH,e3", "e2,SOUTH,e1", "e2,EAST,f2", "e2,WEST,d2",
            "e2,NORTHEAST,f3", "e2,SOUTHEAST,f1", "e2,SOUTHWEST,d1", "e2,NORTHWEST,d3"
    })
    @DisplayName("킹은 모든 방향으로 1칸씩 움직일 수 있다.")
    void kingMove(String sourcePosition, String rawDirection, String expectedPosition) {
        //given
        King king = King.createBlack();

        //when
        Position nextPosition = king.nextPosition(sourcePosition, Direction.valueOf(rawDirection), 7);

        //then
        assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @CsvSource({
            "e1,SOUTH", "a1,WEST", "a8,NORTH", "h8,EAST"
    })
    @DisplayName("체스판 밖으로 움직이려고 시도하면 예외가 발생한다.")
    void kingMoveWhenBoardOutThenException(String sourcePosition, String rawDirection) {
        //given
        King king = King.createBlack();

        //when
        Exception exception = catchException(
                () -> king.nextPosition(sourcePosition, Direction.valueOf(rawDirection), 1));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }
}
