package com.wootecam.chess.pieces;

import com.wootecam.chess.CoordinatesExtractor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            d4, d5, NORTH
            d4, e4, EAST
            d4, d3, SOUTH
            d4, c4, WEST
            d4, e5, NORTHEAST
            d4, e3, SOUTHEAST
            d4, c3, SOUTHWEST
            d4, c5, NORTHWEST
            """
    )
    void 킹은_모든방향으로_1칸_움직인다(String start, String target, String directionValue) {
        // given
        King king = new King(Color.BLACK);
        CoordinatesExtractor extractor = new CoordinatesExtractor();
        Position startPosition = extractor.extractPosition(start);
        Position endPosition = extractor.extractPosition(target);
        Direction direction = Direction.valueOf(directionValue);

        // when
        Direction actual = king.findDirection(startPosition, endPosition).get();

        // then
        Assertions.assertThat(actual).isEqualTo(direction);
    }

}
