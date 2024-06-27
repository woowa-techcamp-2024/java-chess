package pieces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceDescriptorTest {
    @Test
    public void pieceDescriptor()
    {
        assertEquals(PieceUnicode.BLANK, PieceDescriptor.getUnicode(PieceColor.NO_COLOR, PieceType.NO_PIECE));

        assertEquals(PieceUnicode.BLACK_PAWN, PieceDescriptor.getUnicode(PieceColor.BLACK, PieceType.PAWN));
        assertEquals(PieceUnicode.BLACK_ROOK, PieceDescriptor.getUnicode(PieceColor.BLACK, PieceType.ROOK));
        assertEquals(PieceUnicode.BLACK_KNIGHT, PieceDescriptor.getUnicode(PieceColor.BLACK, PieceType.KNIGHT));
        assertEquals(PieceUnicode.BLACK_BISHOP, PieceDescriptor.getUnicode(PieceColor.BLACK, PieceType.BISHOP));
        assertEquals(PieceUnicode.BLACK_QUEEN, PieceDescriptor.getUnicode(PieceColor.BLACK, PieceType.QUEEN));
        assertEquals(PieceUnicode.BLACK_KING, PieceDescriptor.getUnicode(PieceColor.BLACK, PieceType.KING));

        assertEquals(PieceUnicode.WHITE_PAWN, PieceDescriptor.getUnicode(PieceColor.WHITE, PieceType.PAWN));
        assertEquals(PieceUnicode.WHITE_ROOK, PieceDescriptor.getUnicode(PieceColor.WHITE, PieceType.ROOK));
        assertEquals(PieceUnicode.WHITE_KNIGHT, PieceDescriptor.getUnicode(PieceColor.WHITE, PieceType.KNIGHT));
        assertEquals(PieceUnicode.WHITE_BISHOP, PieceDescriptor.getUnicode(PieceColor.WHITE, PieceType.BISHOP));
        assertEquals(PieceUnicode.WHITE_QUEEN, PieceDescriptor.getUnicode(PieceColor.WHITE, PieceType.QUEEN));
        assertEquals(PieceUnicode.WHITE_KING, PieceDescriptor.getUnicode(PieceColor.WHITE, PieceType.KING));
    }
}
