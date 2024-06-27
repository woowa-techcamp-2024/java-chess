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

    @Test
    @DisplayName("현재 남아있는 기물에 따라 점수를 계산해야합니다.")
    public void calculatePoint1() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("현재 남아있는 기물에 따라 점수를 계산해야합니다.")
    public void calculatePoint2() throws Exception {
        board.initializeEmpty();

        addPiece("f2", Piece.createWhitePawn());
        addPiece("h3", Piece.createWhitePawn());
        addPiece("h3", Piece.createWhitePawn());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("g4", Piece.createWhiteQueen());
        addPiece("f4", Piece.createWhiteKnight());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(19.5, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("현재 남아있는 기물들을 색과 점수에 따라 정렬합니다.")
    public void sortPieces() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("h3", Piece.createWhitePawn());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("g4", Piece.createWhiteQueen());
        addPiece("f4", Piece.createWhiteKnight());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals("KPRQ", new Rank(board.sortPiece(Color.BLACK, Order.ASC)).toString());
        assertEquals("kppppnrq", new Rank(board.sortPiece(Color.WHITE, Order.ASC)).toString());

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
