package org.example;

import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    @DisplayName("기본 생성자로 폰이 생성되어야 한다")
    @Test
    public void create() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Pawn.WHITE);
    }

    @DisplayName("색깔에 맞는 폰이 생성되어야 한다")
    @ParameterizedTest(name = "폰의 색깔은 {1} 이어야 한다")
    @MethodSource("argumentsStream")
    public void create(String nowColor, String expectedColor) {
        Pawn pawn = new Pawn(nowColor);
        assertThat(pawn.getColor()).isEqualTo(expectedColor);
    }


    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
            Arguments.arguments(Pawn.WHITE, Pawn.WHITE),
            Arguments.arguments(Pawn.BLACK, Pawn.BLACK)
        );
    }

}