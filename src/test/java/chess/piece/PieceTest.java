package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("각 기물들이 정상적으로 생성된다.")
    public void create_piece() {
        verifyPiece(Pawn.create(PieceColor.WHITE), Pawn.create(PieceColor.BLACK), Type.PAWN);
        verifyPiece(Knight.create(PieceColor.WHITE), Knight.create(PieceColor.BLACK), Type.KNIGHT);
        verifyPiece(Rook.create(PieceColor.WHITE), Rook.create(PieceColor.BLACK), Type.ROOK);
        verifyPiece(Bishop.create(PieceColor.WHITE), Bishop.create(PieceColor.BLACK), Type.BISHOP);
        verifyPiece(Queen.create(PieceColor.WHITE), Queen.create(PieceColor.BLACK), Type.QUEEN);
        verifyPiece(King.create(PieceColor.WHITE), King.create(PieceColor.BLACK), Type.KING);

        Piece blank = Blank.create();
        assertNotSame(blank.getColor(), PieceColor.WHITE);
        assertNotSame(blank.getColor(), PieceColor.BLACK);
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertSame(whitePiece.getColor(), PieceColor.WHITE);
        assertEquals(type, whitePiece.getType());

        assertSame(blackPiece.getColor(), PieceColor.BLACK);
        assertEquals(type, blackPiece.getType());
    }
}