package chess.board;

import chess.pieces.Piece;
import chess.pieces.Piece.Type;
import chess.pieces.Piece.Color;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

public class BoardTest {

    private Board board;
    private String blankRank = appendNewLine("........");

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 초기화하고 처음 상태를 출력합니다.")
    public void create() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("현재 체스판에 있는 기물의 개수를 반환하는 해야합니다.")
    public void countPiece() throws Exception {
        board.initialize();
        assertEquals(board.countPiece(Color.BLACK, Type.BISHOP), 2);
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회해야합니다.")
    public void findPiece() throws Exception {
        board.initialize();
        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
        assertEquals(Piece.createWhiteKing(), board.findPiece("e1"));
    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가할 수 있어야 합니다")
    public void move() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

}
