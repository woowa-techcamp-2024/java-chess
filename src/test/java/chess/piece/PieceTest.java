package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {

        verifyPiece(Pawn.create(PieceColor.WHITE), PieceColor.WHITE, PieceRepresentation.PAWN_WHITE);

        verifyPiece(Pawn.create(PieceColor.BLACK), PieceColor.BLACK, PieceRepresentation.PAWN_BLACK);
    }

    private void verifyPiece(final Piece piece, final PieceColor color, final PieceRepresentation representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(PieceRepresentation.getPieceRepresentation(piece.getColor(), piece.getType()))
                .isEqualTo(representation.getPieceRepresentation());
    }
}