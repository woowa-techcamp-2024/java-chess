package chess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pieces.PieceColor;
import pieces.PieceType;
import pieces.PieceUnicode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private final int NUM_COL = 8;
    private final String BLANK = ".";
    private static Board board;
    @BeforeAll
    public static void beforeAll()
    {
        board = new Board();
        board.initialize();
    }

    @Test
    public void initialize() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb
                .append(generateBlackInitLine()).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(generateWhiteInitLine());
        assertEquals(sb.toString().strip(), board.show());
        assertEquals(NUM_COL*4, board.getNumOfPieces());
    }

    private String generateBlackInitLine() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(PieceUnicode.BLACK_ROOK.getUnicode())
                .append(PieceUnicode.BLACK_KNIGHT.getUnicode())
                .append(PieceUnicode.BLACK_BISHOP.getUnicode())
                .append(PieceUnicode.BLACK_QUEEN.getUnicode())
                .append(PieceUnicode.BLACK_KING.getUnicode())
                .append(PieceUnicode.BLACK_BISHOP.getUnicode())
                .append(PieceUnicode.BLACK_KNIGHT.getUnicode())
                .append(PieceUnicode.BLACK_ROOK.getUnicode())
                .append("\n")
                .append(PieceUnicode.BLACK_PAWN.getUnicode().repeat(NUM_COL));
        return sb.toString();
    }

    private String generateWhiteInitLine() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(PieceUnicode.WHITE_PAWN.getUnicode().repeat(NUM_COL))
                .append("\n")
                .append(PieceUnicode.WHITE_ROOK.getUnicode())
                .append(PieceUnicode.WHITE_KNIGHT.getUnicode())
                .append(PieceUnicode.WHITE_BISHOP.getUnicode())
                .append(PieceUnicode.WHITE_QUEEN.getUnicode())
                .append(PieceUnicode.WHITE_KING.getUnicode())
                .append(PieceUnicode.WHITE_BISHOP.getUnicode())
                .append(PieceUnicode.WHITE_KNIGHT.getUnicode())
                .append(PieceUnicode.WHITE_ROOK.getUnicode());
        return sb.toString();
    }

    @Test
    public void countColorPiece(){
        assertEquals(NUM_COL, board.countColorPiece(PieceColor.BLACK, PieceType.PAWN));
        assertEquals(2, board.countColorPiece(PieceColor.BLACK, PieceType.ROOK));
        assertEquals(2, board.countColorPiece(PieceColor.BLACK, PieceType.KNIGHT));
        assertEquals(2, board.countColorPiece(PieceColor.BLACK, PieceType.BISHOP));
        assertEquals(1, board.countColorPiece(PieceColor.BLACK, PieceType.QUEEN));
        assertEquals(1, board.countColorPiece(PieceColor.BLACK, PieceType.KING));

        assertEquals(NUM_COL, board.countColorPiece(PieceColor.WHITE, PieceType.PAWN));
        assertEquals(2, board.countColorPiece(PieceColor.WHITE, PieceType.ROOK));
        assertEquals(2, board.countColorPiece(PieceColor.WHITE, PieceType.KNIGHT));
        assertEquals(2, board.countColorPiece(PieceColor.WHITE, PieceType.BISHOP));
        assertEquals(1, board.countColorPiece(PieceColor.WHITE, PieceType.QUEEN));
        assertEquals(1, board.countColorPiece(PieceColor.WHITE, PieceType.KING));
    }
}
