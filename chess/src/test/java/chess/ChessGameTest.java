package chess;

import org.junit.jupiter.api.Test;
import pieces.PieceFactory;
import pieces.PieceUnicode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChessGameTest {
    private final int NUM_COL = 8;
    private final String BLANK = ".";

    private ChessGame chessGame = new ChessGame();
    private PieceFactory pieceFactory = new PieceFactory();

    public ChessGameTest() throws Exception {
    }

    @Test
    public void initialize() throws Exception {
        chessGame.initialize();
        assertEquals(initString(), chessGame.showBoard());
    }

    @Test
    public void move() throws Exception {
        chessGame.setPiece("b2", pieceFactory.createWhitePawn(new Position("b2")));
        // when
        chessGame.move("b2", "b3");
        // then
        assertEquals(pieceFactory.createBlank(new Position("b2")), chessGame.findPiece("b2"));
        assertEquals(pieceFactory.createWhitePawn(new Position("b3")), chessGame.findPiece("b3"));
    }


    private String initString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(generateBlackInitLine()).append("\n")
                .append(BLANK.repeat(NUM_COL)).append(" ").append(6).append("\n")
                .append(BLANK.repeat(NUM_COL)).append(" ").append(5).append("\n")
                .append(BLANK.repeat(NUM_COL)).append(" ").append(4).append("\n")
                .append(BLANK.repeat(NUM_COL)).append(" ").append(3).append("\n")
                .append(generateWhiteInitLine()).append("\n")
                .append("abcdefgh");
        return sb.toString().strip();
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
                .append(" " +  8)
                .append("\n")
                .append(PieceUnicode.BLACK_PAWN.getUnicode().repeat(NUM_COL))
                .append(" " + 7);
        return sb.toString();
    }

    private String generateWhiteInitLine() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(PieceUnicode.WHITE_PAWN.getUnicode().repeat(NUM_COL)).append(" ").append(2)
                .append("\n")
                .append(PieceUnicode.WHITE_ROOK.getUnicode())
                .append(PieceUnicode.WHITE_KNIGHT.getUnicode())
                .append(PieceUnicode.WHITE_BISHOP.getUnicode())
                .append(PieceUnicode.WHITE_QUEEN.getUnicode())
                .append(PieceUnicode.WHITE_KING.getUnicode())
                .append(PieceUnicode.WHITE_BISHOP.getUnicode())
                .append(PieceUnicode.WHITE_KNIGHT.getUnicode())
                .append(PieceUnicode.WHITE_ROOK.getUnicode())
                .append(" ").append(1);
        return sb.toString();
    }
}
