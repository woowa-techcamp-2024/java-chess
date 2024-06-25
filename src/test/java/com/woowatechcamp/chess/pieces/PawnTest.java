package com.woowatechcamp.chess.pieces;

import java.util.List;

import com.woowatechcamp.chess.pieces.Pawn;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo("white");
    }
}
