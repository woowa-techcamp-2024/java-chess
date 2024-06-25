package pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {

    private static final PieceColor WHITE = PieceColor.WHITE;
    private static final PieceColor BLACK = PieceColor.BLACK;

    @Test
    @DisplayName("흰색/검정 폰이 생성되어야 한다")
    public void create()
    {
        verifyPawn(WHITE);
        verifyPawn(BLACK);
    }

    @Test
    @DisplayName("폰 기본 생성자 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Piece piece = new Piece();
        assertEquals(WHITE, piece.color());
    }

    private void verifyPawn(final PieceColor color){
        Piece piece = new Piece(color);
        assertThat(piece.color()).isEqualTo(color);
    }
}
