package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {

    private Piece piece;

    @Test
    @DisplayName("생성자에 전달된 색의 폰이 생성되어야 한다")
    public void create() {
        piece = new Pawn(Color.WHITE);
        verifyPiece(Color.WHITE);

        piece = new Pawn(Color.BLACK);
        verifyPiece(Color.BLACK);
    }

    @Test
    @DisplayName("생성자에 색이 전달되지 않을 경우 흰색 폰이 생성되어야 한다")
    public void create_defaultConstructor() {
        piece = new Pawn();
        verifyPiece(Color.WHITE);
    }

    private void verifyPiece(final Color color) {
        assertThat(piece.getColor()).isEqualTo(color);
    }

}
