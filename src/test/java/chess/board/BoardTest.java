package chess.board;

import chess.pieces.*;
import chess.pieces.Piece.Type;
import chess.pieces.Piece.Color;
import chess.view.ChessView;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

public class BoardTest {

    private Board board;
    private ChessView chessView;

    private final String blankRank = appendNewLine("........");

    @BeforeEach
    public void setup() {
        board = new Board();
        chessView = new ChessView();
    }

    @Test
    @DisplayName("체스판을 초기화하고 처음 상태를 출력합니다.")
    public void create() throws Exception {
        board.initialize();

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                chessView.showBoard(board));
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
        assertEquals(Rook.createBlackRook(), board.findPiece("a8"));
        assertEquals(Rook.createBlackRook(), board.findPiece("h8"));
        assertEquals(Rook.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Rook.createWhiteRook(), board.findPiece("h1"));
        assertEquals(King.createWhiteKing(), board.findPiece("e1"));
    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가할 수 있어야 합니다")
    public void addPiece() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Rook.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(chessView.showBoard(this.board));
    }

    @Test
    @DisplayName("현재 남아있는 기물에 따라 점수를 계산해야합니다.")
    public void calculatePoint1() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Pawn.createBlackPawn());
        addPiece("e6", Queen.createBlackQueen());
        addPiece("b8", King.createBlackKing());
        addPiece("c8", Rook.createBlackRook());

        addPiece("f2", Pawn.createWhitePawn());
        addPiece("g2", Pawn.createWhitePawn());
        addPiece("e1", Rook.createWhiteRook());
        addPiece("f1", King.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(chessView.showBoard(board));
    }

    @Test
    @DisplayName("현재 남아있는 기물에 따라 점수를 계산해야합니다.")
    public void calculatePoint2() throws Exception {
        board.initializeEmpty();

        addPiece("f2", Pawn.createWhitePawn());
        addPiece("h3", Pawn.createWhitePawn());
        addPiece("h3", Pawn.createWhitePawn());
        addPiece("f3", Pawn.createWhitePawn());
        addPiece("g4", Queen.createWhiteQueen());
        addPiece("f4", Knight.createWhiteKnight());
        addPiece("g2", Pawn.createWhitePawn());
        addPiece("e1", Rook.createWhiteRook());
        addPiece("f1", King.createWhiteKing());

        assertEquals(19.5, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(chessView.showBoard(board));
    }

    @Test
    @DisplayName("현재 남아있는 기물들을 색과 점수에 따라 정렬합니다.")
    public void sortPieces() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Pawn.createBlackPawn());
        addPiece("e6", Queen.createBlackQueen());
        addPiece("b8", King.createBlackKing());
        addPiece("c8", Rook.createBlackRook());

        addPiece("f2", Pawn.createWhitePawn());
        addPiece("h3", Pawn.createWhitePawn());
        addPiece("f3", Pawn.createWhitePawn());
        addPiece("g4", Queen.createWhiteQueen());
        addPiece("f4", Knight.createWhiteKnight());
        addPiece("g2", Pawn.createWhitePawn());
        addPiece("e1", Rook.createWhiteRook());
        addPiece("f1", King.createWhiteKing());

        assertEquals("KPRQ", new Rank(board.sortPiece(Color.BLACK, Order.ASC)).toString());
        assertEquals("kppppnrq", new Rank(board.sortPiece(Color.WHITE, Order.ASC)).toString());

        System.out.println(chessView.showBoard(board));
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    @Test
    @DisplayName("기물이 현재 위치에서 다른 위치로 이동할 수 있어야 합니다.")
    public void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        assertEquals(Blank.createBlank(), board.findPiece(sourcePosition));
        assertEquals(Pawn.createWhitePawn(), board.findPiece(targetPosition));

        System.out.println(chessView.showBoard(board));
    }
}
