package com.woowatechcamp;import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    @Test
    @DisplayName("흰색, 검은색 폰이 생성되어야 한다")
    public void create() {
        List<String> colors = List.of("white", "black");
        for (String color : colors) {
            Pawn pawn = new Pawn(color);
            assertThat(pawn.getColor()).isEqualTo(color);
        }
    }
}
