package chess;

import chess.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드엔 폰이 추가되고 폰에 대한 정보를 얻을 수 있다.")
    public void create() throws Exception {
        Position whitePosition = Position.of(1, 2);
        Pawn white = addPawn(board, whitePosition, PieceColor.WHITE);
        assertEquals(1, board.pieceCount());
        assertEquals(white, board.findPiece(whitePosition));

        Position blackPosition = Position.of(2, 7);
        Pawn black = addPawn(board, blackPosition, PieceColor.BLACK);
        assertEquals(2, board.pieceCount());
        assertEquals(black, board.findPiece(blackPosition));
    }

    private Pawn addPawn(final Board board, final Position position, final PieceColor color) {
        Pawn pawn = Pawn.create(color);
        board.add(position, pawn);

        return pawn;
    }

    @Test
    @DisplayName("보드 초기화시 기물이 각 개수에 알맞게 생성된다.")
    public void initializePawn() throws Exception {
        expectedPiece(Type.PAWN, 8);
        expectedPiece(Type.QUEEN, 1);
        expectedPiece(Type.ROOK, 2);
        expectedPiece(Type.KING, 1);
        expectedPiece(Type.BISHOP, 2);
        expectedPiece(Type.KNIGHT, 2);
    }

    private void expectedPiece(Type type, int expected) {
        board.initialize();

        assertEquals(expected, board.getPieceResult(PieceColor.WHITE, type));
        assertEquals(expected, board.getPieceResult(PieceColor.BLACK, type));
    }

    @Test
    @DisplayName("보드 출력시 기물이 정상적으로 출력된다.")
    public void print() {
        board.initialize();
        board.print();

        assertEquals(32, board.pieceCount());

        assertEquals(
                appendNewLine("♜♞♝♛♚♝♞♜8") +
                        appendNewLine("♟♟♟♟♟♟♟♟7") +
                        appendNewLine("........6") +
                        appendNewLine("........5") +
                        appendNewLine("........4") +
                        appendNewLine("........3") +
                        appendNewLine("♙♙♙♙♙♙♙♙2") +
                        appendNewLine("♖♘♗♕♔♗♘♖1") +
                        appendNewLine("abcdefgh"),
                board.showBoard());
    }

    @Test
    @DisplayName("포지션에 해당하는 기물을 찾는다.")
    public void findPiece() throws Exception {
        board.initialize();

        verifyRookPiece("a8", PieceColor.BLACK);
        verifyRookPiece("h8", PieceColor.BLACK);
        verifyRookPiece("a1", PieceColor.WHITE);
        verifyRookPiece("h1", PieceColor.WHITE);
        verifyKingPiece("e8", PieceColor.BLACK);
        verifyKingPiece("e1", PieceColor.WHITE);
    }

    private void verifyRookPiece(final String point, final PieceColor pieceColor) {
        assertEquals(Rook.create(pieceColor).getType(), board.findPiece(point).getType());
        assertEquals(Rook.create(pieceColor).getColor(), board.findPiece(point).getColor());
    }

    private void verifyKingPiece(final String point, final PieceColor pieceColor) {
        assertEquals(King.create(pieceColor).getType(), board.findPiece(point).getType());
        assertEquals(King.create(pieceColor).getColor(), board.findPiece(point).getColor());
    }
}
