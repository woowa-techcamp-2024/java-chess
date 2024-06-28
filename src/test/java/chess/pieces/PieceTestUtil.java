package chess.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.board.Board;

public class PieceTestUtil {
    public static void assertMove(Board board, Position src, Position dest, Piece piece) {
        assertEquals(PieceFactory.createBlankPiece(), board.findPiece(src));
        assertEquals(piece, board.findPiece(dest));
    }

    public static void assertImpossibleMove(Board board, Position src, Position target) {
        assertThrows(Exception.class, () -> board.move(src, target));
    }
}
