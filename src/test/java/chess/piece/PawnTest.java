package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {

        verifyPawn(PieceColor.WHITE, PieceRepresentation.PAWN_WHITE);

        verifyPawn(PieceColor.BLACK, PieceRepresentation.PAWN_BLACK);
    }

    private void verifyPawn(final PieceColor color, final PieceRepresentation representation) {
        Pawn pawn = new Pawn(color);

        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(PieceRepresentation.getPieceRepresentation(pawn.getColor(), pawn.getType()))
                .isEqualTo(representation.getPieceRepresentation());
    }
}