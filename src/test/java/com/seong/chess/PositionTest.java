package com.seong.chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @ParameterizedTest
    @CsvSource({
            "0,0,a8", "7,7,h1", "2,2,c6"
    })
    @DisplayName("행렬 포지션을 체스 포지션으로 컨버팅할 수 있다.")
    public void convertToRawPosition(int row, int col, String expected) {
        //given
        Position position = new Position(col, row);

        //when
        String rawPosition = position.convert();

        //then
        assertThat(rawPosition).isEqualTo(expected);
    }
}
