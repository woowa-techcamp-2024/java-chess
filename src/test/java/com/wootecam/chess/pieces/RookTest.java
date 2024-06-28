package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.chess.game.CoordinatesExtractor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {


    @ParameterizedTest
    @CsvSource(textBlock = """
            d4, d8, NORTH
            d4, h4, EAST
            d4, d1, SOUTH
            d4, a4, WEST
            """
    )
    void 룩은_네가지_직선방향으로_끝까지_움직일_수_있다(String start, String target, String directionValue) {
        // given
        Rook rook = new Rook(Color.BLACK);
        CoordinatesExtractor extractor = new CoordinatesExtractor();
        Position startPosition = extractor.extractPosition(start);
        Position endPosition = extractor.extractPosition(target);
        Direction direction = Direction.valueOf(directionValue);

        // when
        Direction actual = rook.findDirection(startPosition, endPosition).get();

        // then
        assertThat(actual).isEqualTo(direction);
    }
}
