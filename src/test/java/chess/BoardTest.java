package chess;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드에 폰을 추가할 수 있다")
    public void create() {
        board.initialize();
        assertEquals(32, board.pieceCount());
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
    @DisplayName("체스판을 출력할 수 있다")
    public void showBoard() {
        board.initialize();
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환한다")
    public void countPieces() {
        board.initialize();
        assertEquals(8, board.countPieces(Color.BLACK, Type.PAWN));

        board.updateBoard(List.of(
                ".KR.....",
                "P.PB....",
                ".P..Q...",
                "........",
                ".....nq.",
                ".....p..",
                "......p.",
                "....rk.."
        ));
        System.out.println(board.showBoard());
        assertEquals(3, board.countPieces(Color.BLACK, Type.PAWN));
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회할 수 있다")
    public void findPiece() {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }
}
