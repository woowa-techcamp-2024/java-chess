package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

class BoardTest {
    Board board;
    String initializedBoard;

    @BeforeEach()
    public void setUp() {
        board = new Board();
        initializedBoard =
                """
                   ........
                   PPPPPPPP
                   ........
                   ........
                   ........
                   ........
                   pppppppp
                   ........
                   """;
    }

    @Test
    void initialize() {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    void create() {
        board.initialize();
        assertEquals(32, board.getPieceCount());
        System.out.println(board.showBoard());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp")+
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("체스 판에 Pawn 이외의 객체가 추가되지 않도록 한다.")
    void add_not_Pawn() {
        assertThatThrownBy(() -> {
            Object o = new Object();
            board.add((Piece) o);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환")
    void findPiece() {
        board.initialize();

        assertEquals(8, board.findPiece(Piece.Color.BLACK, Piece.Type.PAWN));
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회")
    void findPiece_by_pos() {
        board.initialize();

        assertEquals(Piece.createBlack(Piece.Type.ROOK).getType(), board.findPiece("a8").getType());
        assertEquals(Piece.createBlack(Piece.Type.ROOK).getType(), board.findPiece("h8").getType());
        assertEquals(Piece.createWhite(Piece.Type.ROOK).getType(), board.findPiece("a1").getType());
        assertEquals(Piece.createWhite(Piece.Type.ROOK).getType(), board.findPiece("h1").getType());

    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가")
    void addPiece_by_pos() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlack(Piece.Type.ROOK);
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }
}
