package javachess.chess.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PieceUtils {

    public static void assertIsBlack(Piece piece) {
        assertThat(piece.isBlack()).isTrue();
    }

    public static void assertIsWhite(Piece piece) {
        assertThat(piece.isWhite()).isTrue();
    }

    public static void assertNotMoved(Piece piece) {
        assertThat(piece.notMoved()).isTrue();
    }

    public static void assertIsMoved(Piece piece) {
        assertThat(piece.notMoved()).isFalse();
    }

}
