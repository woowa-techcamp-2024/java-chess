package pe.goblin.pawn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnTest {
    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void createWhitePawn() {
        Pawn pawn = new Pawn("white");
        assertThat(pawn.getColor()).isEqualTo("white");
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    void createBlackPawn() {
        Pawn pawn = new Pawn("black");
        assertThat(pawn.getColor()).isEqualTo("black");
    }

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    void createWhitePawnConcrete() {
        final String colorToTest = "white";
        verifyPawn(colorToTest);
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    void createBlackPawnConcrete() {
        final String colorToTest = "black";
        verifyPawn(colorToTest);
    }

    void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    void create_기본생성자() {
        Pawn pawn = new Pawn();
        assertEquals("white", pawn.getColor());
    }
}