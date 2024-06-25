package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.chess.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("폰 테스트")
class PawnTest {

    @EnumSource(Color.class)
    @ParameterizedTest(name = "{0} 색 폰이 생성되어야 한다")
    void 해당_색을_가진_폰이_생성되어야_한다(Color color) {
        var pawn = new Pawn(color);

        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    void 색깔이_주어지지_않았다면_하얀색_폰이_생성되어야_한다() {
        var pawn = new Pawn();

        assertThat(pawn.getColor()).isEqualTo(Color.WHITE);
    }
}
