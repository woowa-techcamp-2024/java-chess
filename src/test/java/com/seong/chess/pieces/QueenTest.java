package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.seong.chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource({"d5,NORTH,3,d8", "d5,SOUTH,4,d1", "d5,WEST,3,a5", "d5,EAST,4,h5", "d5,NORTHEAST,3,g8",
            "d5,SOUTHEAST,4,h1", "d5,SOUTHWEST,3,a2", "d5,NORTHWEST,3,a8"})
    @DisplayName("퀸은 모든 방향으로 이동할 수 있다.")
    void moveQueen(String sourcePosition, String rawDirection, int moveCount, String expectedPosition) {
        //given
        Queen queen = Queen.createBlack();

        //when
        Position nextPosition = queen.nextPosition(sourcePosition, Direction.valueOf(rawDirection), moveCount);

        //then
        assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @CsvSource({"d5,NORTH,100", "d5,SOUTH,100", "d5,WEST,100", "d5,EAST,100"})
    @DisplayName("체스판 밖으로 움직이려고 시도하면 예외가 발생한다.")
    void moveQueenOverChessBoard(String sourcePosition, String rawDirection, int moveCount) {
        //given
        Queen queen = Queen.createBlack();

        //when
        Exception exception = catchException(
                () -> queen.nextPosition(sourcePosition, Direction.valueOf(rawDirection), moveCount));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }
}