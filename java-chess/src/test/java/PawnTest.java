import org.example.character.Pawn;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    private static final String white = "white";
    private static final String black = "black";

    @ParameterizedTest
    @ValueSource(strings = {white, black})
    @DisplayName("폰이 생성되는 색깔에 맞게 생성되어야 한다")
    public void create(String colors) {
        verifyPawn(colors);
    }

    public void verifyPawn(String color) {
        Pawn pawn = new Pawn(color);

        if (color.equals(white)) {
            assertThat(pawn.getColor()).isEqualTo(white);
        } else {
            assertThat(pawn.getColor()).isEqualTo(black);
        }
    }
}