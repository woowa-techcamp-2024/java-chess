package org.example.chess;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @Nested
    @DisplayName("정상 포지션 생성 테스트")
    class ValidPositionTests {

        @ParameterizedTest(name = "{index} => position={0}, expectedCol={1}, expectedRow={2}, expectedColIdx={3}")
        @MethodSource("validPositionProvider")
        @DisplayName("정상 포지션 생성")
        void createValidPosition(String pos, char expectedCol, int expectedRow, int expectedColIdx) {
            Position position = new Position(pos);
            assertAll("position",
                () -> assertEquals(expectedCol, position.getCol()),
                () -> assertEquals(expectedRow, position.getRow()),
                () -> assertEquals(expectedColIdx, position.getColIdx())
            );
        }

        static Stream<Arguments> validPositionProvider() {
            return Stream.of(
                Arguments.of("a1", 'a', 1, 0),
                Arguments.of("h8", 'h', 8, 7),
                Arguments.of("d5", 'd', 5, 3)
            );
        }
    }

    @Nested
    @DisplayName("포지션 생성시 예외 테스트")
    class InvalidPositionTests {

        @ParameterizedTest(name = "{index} => invalidPosition={0}")
        @MethodSource("invalidPositionProvider")
        @DisplayName("포지션 생성시 예외 발생")
        void createInvalidPosition(String invalidPosition) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Position(invalidPosition));
            // 예외 메시지를 상황에 맞게 검증합니다.
            if (invalidPosition.length() != 2) {
                assertEquals("position length must be 2", exception.getMessage());
            } else if (invalidPosition.charAt(0) < 'a' || invalidPosition.charAt(0) > 'h') {
                assertEquals("first character must be a~h", exception.getMessage());
            } else if (invalidPosition.charAt(1) < '1' || invalidPosition.charAt(1) > '8') {
                assertEquals("second character must be 1~8", exception.getMessage());
            }
        }

        static Stream<String> invalidPositionProvider() {
            return Stream.of("a", "a12", "", "i1", "z2", "a9", "a0");
        }
    }
}
