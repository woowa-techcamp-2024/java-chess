package woowa.camp.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("[Success] 색상에 맞는 폰이 생성되어야 한다")
    void create() {
        Piece pieceWhite = new Piece(Color.PAWN_WHITE);
        Piece pieceBlack = new Piece(Color.PAWN_BLACK);

        verifyPawnColor(pieceWhite, Color.PAWN_WHITE.getName(), Color.PAWN_WHITE.getRepresentation());
        verifyPawnColor(pieceBlack, Color.PAWN_BLACK.getName(), Color.PAWN_BLACK.getRepresentation());

        assertThat(pieceWhite.getRepresentation()).isEqualTo(Color.PAWN_WHITE.getRepresentation());
        assertThat(pieceBlack.getRepresentation()).isEqualTo(Color.PAWN_BLACK.getRepresentation());
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Piece piece = new Piece();
        verifyPawnColor(piece, Color.PAWN_WHITE.getName(), Color.PAWN_WHITE.getRepresentation());
    }

    private void verifyPawnColor(final Piece piece, final String color, final String representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

}
