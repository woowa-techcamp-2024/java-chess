package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
import com.seong.chess.pieces.Piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource({
            "d5,NORTH,d8", "d5,SOUTH,d1", "d5,WEST,a5", "d5,EAST,h5",
            "d5,NORTHEAST,g8", "d5,SOUTHEAST,h1", "d5,SOUTHWEST,a2", "d5,NORTHWEST,a8"
    })
    @DisplayName("퀸은 모든 방향으로 이동할 수 있다.")
    void moveQueen(String sourcePosition, String rawDirection, String expectedPosition) {
        //given
        Queen queen = Queen.createBlack();

        //when
        Position nextPosition = queen.nextPosition(sourcePosition, Direction.valueOf(rawDirection), 10);

        //then
        assertThat(nextPosition.convert()).isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("체스판 밖으로 움직이려고 시도하면 무시한다.")
    void moveQueenOverChessBoard() {
        //given
        Queen queen = Queen.createBlack();

        //when
        Position nextPosition = queen.nextPosition("a1", Direction.NORTH, 100);

        //then
        assertThat(nextPosition.convert()).isEqualTo("a8");
    }
}