import static org.assertj.core.api.Assertions.assertThat;

import com.wootecam.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("폰 테스트")
public class PawnTest {

    @ValueSource(strings = {"white", "black"})
    @ParameterizedTest(name = "{0} 색 폰이 생성되어야 한다")
    public void 해당_색을_가진_폰이_생성되어야_한다(String color) {
        var pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
