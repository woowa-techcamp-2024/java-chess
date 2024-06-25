package chess;

import chess.piece.Pawn;
import chess.piece.PieceColor;
import chess.piece.Position;
import chess.piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Position whitePosition = new Position(1, 2);
        Pawn white = addPawn(board, whitePosition, PieceColor.WHITE);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(whitePosition));

        Position blackPosition = new Position(2, 7);
        Pawn black = addPawn(board, blackPosition, PieceColor.BLACK);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(blackPosition));
    }

    private Pawn addPawn(final Board board, final Position position, final PieceColor color) {
        Pawn pawn = new Pawn(color);
        board.add(position, pawn);

        return pawn;
    }

    @Test
    @DisplayName("보드 초기화시 색별로 폰이 8개씩 생성된다.")
    public void initialize() throws Exception {
        board.initialize();

        assertEquals("♙♙♙♙♙♙♙♙", board.getPieceResult(PieceColor.WHITE, Type.PAWN));
        assertEquals("♟♟♟♟♟♟♟♟", board.getPieceResult(PieceColor.BLACK, Type.PAWN));
    }

    @Test
    @DisplayName("보드 출력시 기물이 정상적으로 출력된다.")
    public void print() {
        board.initialize();

        String print = board.print();

        System.out.println(print);
    }
}
