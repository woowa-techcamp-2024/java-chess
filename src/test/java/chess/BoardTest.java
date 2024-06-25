package chess;

import chess.piece.Pawn;
import chess.piece.PieceColor;
import chess.piece.Position;
import chess.piece.Type;
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
        String blankRank = appendNewLine("........");

        assertEquals(
                appendNewLine("♖♘♗♕♔♗♘♖") +
                        appendNewLine("♙♙♙♙♙♙♙♙") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("♟♟♟♟♟♟♟♟") +
                        appendNewLine("♜♞♝♛♚♝♞♜"),
                board.showBoard());
    }
}
