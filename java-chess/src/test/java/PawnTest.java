import org.example.character.Pawn;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    private static final Pawn.Color white = Pawn.Color.WHITE;
    private static final Pawn.Color black = Pawn.Color.BLACK;

    @ParameterizedTest
    @EnumSource(Pawn.Color.class)
    @DisplayName("폰이 생성되는 색깔에 맞게 생성되어야 한다")
    public void create(Pawn.Color colors) {
        verifyPawn(colors);
    }

    public void verifyPawn(Pawn.Color color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}