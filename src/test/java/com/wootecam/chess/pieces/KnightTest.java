package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.chess.game.CoordinatesExtractor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            d4, e6, NNE
            d4, c6, NNW
            d4, e2, SSE
            d4, c2, SSW
            d4, f5, EEN
            d4, f3, EES
            d4, b5, WWN
            d4, b3, WWS
            """
    )
    void 나이트는_모든방향으로_전진_대각으로_움직인다(String start, String target, String directionValue) {
        // given
        Knight king = new Knight(Color.BLACK);
        CoordinatesExtractor extractor = new CoordinatesExtractor();
        Position startPosition = extractor.extractPosition(start);
        Position endPosition = extractor.extractPosition(target);
        Direction direction = Direction.valueOf(directionValue);

        // when
        Direction actual = king.findDirection(startPosition, endPosition).get();

        // then
        assertThat(actual).isEqualTo(direction);
    }
}
