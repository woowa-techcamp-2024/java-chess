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

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가할 수 있다")
    public void move() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("남아있는 기물에 대한 점수 계산이 가능해야한다")
    public void caculcatePoint() {
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

    private void addPiece(final String position, final Piece piece) {
        board.move(position, piece);
    }

    @Test
    @DisplayName("원하는 색상의 기물들을 정렬된 리스트로 받을 수 있어야 한다")
    public void getSortedPieces() {
        board.initialize();
        List<Piece> sortedWhitePieces = board.getSortedPieces(Color.WHITE);
        System.out.println(sortedWhitePieces);
        System.out.println(sortedWhitePieces.reversed());
    }
}
