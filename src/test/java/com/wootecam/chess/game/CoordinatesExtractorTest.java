package com.wootecam.chess.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoordinatesExtractorTest {

    private CoordinatesExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new CoordinatesExtractor();
    }

    @Nested
    class extractPosition_메소드는 {

        @Nested
        class 만약_8x8_크기의_체스판을_벗어나는_좌표를_입력하면 {

            @ParameterizedTest
            @ValueSource(strings = {"a9", "a0", "i5"})
            void 예외가_발생한다(String invalidCoordinate) {
                // expect
                assertThatThrownBy(() -> extractor.extractPosition(invalidCoordinate))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("8 x 8 크기의 체스판의 범위를 벗어나는 좌표입니다. coordinate = " + invalidCoordinate);
            }
        }
    }
}
