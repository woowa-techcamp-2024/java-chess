package org.example.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PawnTest {

    @DisplayName("기본 생성자로 폰이 생성되어야 한다")
    @Test
    public void create() {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
        assertEquals(Pawn.WHITE_REPRESENTATION, pawn.getRepresentation());
    }

    @DisplayName("색깔에 맞는 폰이 생성되어야 한다")
    @ParameterizedTest(name = "폰의 색깔은 {0}이고 표현은 {1}이어야 한다")
    @MethodSource("argumentsStream")
    public void create(String color, String representation) {
        Pawn pawn = new Pawn(color);
        assertEquals(color, pawn.getColor());
        assertEquals(representation, pawn.getRepresentation());
    }

    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
            Arguments.arguments(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION),
            Arguments.arguments(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION)
        );
    }

}