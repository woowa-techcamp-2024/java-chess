package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("폰 테스트")
class PawnTest {

    @Nested
    class 폰을_생성한다 {

        @EnumSource(Color.class)
        @ParameterizedTest(name = "{0} 색 폰이 생성되어야 한다")
        void 해당_색을_가진_폰이_생성되어야_한다(Color color) {
            var pawn = new Pawn(color);

            assertThat(pawn.getColor()).isEqualTo(color);
        }

        @EnumSource(Color.class)
        @ParameterizedTest(name = "{0} 색 폰은 해당 색에 따른 표현값을 반환할 수 있다")
        void 특정_색_폰은_해당_색에_따른_표현값을_반환할_수_있다(Color color) {
            var pawn = new Pawn(color);

            assertThat(pawn.getRepresentation()).isEqualTo(color.getRepresentation());
        }

        @Test
        void 색깔이_주어지지_않았다면_하얀색_폰이_생성되어야_한다() {
            var pawn = new Pawn();

            assertThat(pawn.getColor()).isEqualTo(Color.WHITE);
        }

        @Test
        void 색깔이_주어지지_않았다면_하얀색_폰의_표현값이_반환되어야_한다() {
            var pawn = new Pawn();

            assertThat(pawn.getRepresentation()).isEqualTo(Color.WHITE.getRepresentation());
        }
    }
}

