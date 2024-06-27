package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.chess.CoordinatesExtractor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            d4, h8, NORTHEAST
            d4, g1, SOUTHEAST
            d4, a1, SOUTHWEST
            d4, a7, NORTHWEST
            """
    )
    void 비숍은_네가지_대각선_방향으로_끝까지_움직일_수_있다(String start, String target, String directionValue) {
        // given
        Bishop bishop = new Bishop(Color.BLACK);
        CoordinatesExtractor extractor = new CoordinatesExtractor();
        Position startPosition = extractor.extractPosition(start);
        Position endPosition = extractor.extractPosition(target);
        Direction direction = Direction.valueOf(directionValue);

        // when
        Direction actual = bishop.findDirection(startPosition, endPosition).get();

        // then
        assertThat(actual).isEqualTo(direction);
    }
}
