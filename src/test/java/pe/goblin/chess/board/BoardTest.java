package pe.goblin.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pe.goblin.chess.piece.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pe.goblin.chess.utils.StringUtils.appendNewLine;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("Board는 초기화할 때 흰색 Pawn과 검은색 Pawn을 생성해 저장할 수 있다.")
    void create() throws Exception {
        board.initialize();
        assertEquals(32, board.countAllPieces());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회할 수 있다.")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }
}
