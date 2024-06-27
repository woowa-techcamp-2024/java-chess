package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
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

        assertThat(king.nextPosition(sourcePosition, Direction.NORTH, 7).convert()).isEqualTo("e3");
        assertThat(king.nextPosition(sourcePosition, Direction.SOUTH, 7).convert()).isEqualTo("e1");
        assertThat(king.nextPosition(sourcePosition, Direction.EAST, 7).convert()).isEqualTo("f2");
        assertThat(king.nextPosition(sourcePosition, Direction.WEST, 7).convert()).isEqualTo("d2");
        assertThat(king.nextPosition(sourcePosition, Direction.NORTHEAST, 7).convert()).isEqualTo("f3");
        assertThat(king.nextPosition(sourcePosition, Direction.SOUTHEAST, 7).convert()).isEqualTo("f1");
        assertThat(king.nextPosition(sourcePosition, Direction.SOUTHWEST, 7).convert()).isEqualTo("d1");
        assertThat(king.nextPosition(sourcePosition, Direction.NORTHWEST, 7).convert()).isEqualTo("d3");
    }

    @ParameterizedTest
    @CsvSource({
            "e1,SOUTH", "a1,WEST", "a8,NORTH", "h8,EAST"
    })
    @DisplayName("체스판 밖으로 움직이려고 시도하면 무시한다.")
    void kingMoveWhenBoardOutThenException(String sourcePosition, String rawDirection) {
        //given
        King king = King.createBlack();

        //when
        Position nextPosition = king.nextPosition(sourcePosition, Direction.valueOf(rawDirection), 1);

        //then
        assertThat(nextPosition.convert()).isEqualTo(sourcePosition);
    }
}
