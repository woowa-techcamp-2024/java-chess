package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.seong.chess.pieces.Piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @Test
    @DisplayName("킹은 모든 방향으로 1칸씩 움직일 수 있다.")
    void kingMove() {
        King king = King.createBlack();
        String sourcePosition = "e2";

        assertThat(king.nextPosition(sourcePosition, Direction.NORTH).convert()).isEqualTo("e3");
        assertThat(king.nextPosition(sourcePosition, Direction.SOUTH).convert()).isEqualTo("e1");
        assertThat(king.nextPosition(sourcePosition, Direction.EAST).convert()).isEqualTo("f2");
        assertThat(king.nextPosition(sourcePosition, Direction.WEST).convert()).isEqualTo("d2");
        assertThat(king.nextPosition(sourcePosition, Direction.NORTHEAST).convert()).isEqualTo("f3");
        assertThat(king.nextPosition(sourcePosition, Direction.SOUTHEAST).convert()).isEqualTo("f1");
        assertThat(king.nextPosition(sourcePosition, Direction.SOUTHWEST).convert()).isEqualTo("d1");
        assertThat(king.nextPosition(sourcePosition, Direction.NORTHWEST).convert()).isEqualTo("d3");
    }

    @ParameterizedTest
    @CsvSource({
            "e1,SOUTH", "a1,WEST", "a8,NORTH", "h8,EAST"
    })
    @DisplayName("체스판 밖으로 움직이면 예외가 발생한다.")
    void kingMoveWhenBoardOutThenException(String sourcePosition, String rawDirection) {
        //given
        King king = King.createBlack();

        //when
        Exception exception = catchException(() -> king.nextPosition(sourcePosition, Direction.valueOf(rawDirection)));

        //then
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }
}
