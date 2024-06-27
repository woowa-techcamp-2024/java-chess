package chess;

import chess.constant.Color;
import chess.pieces.Piece;
import chess.constant.Type;
import chess.pieces.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;
    private ChessView chessView;
    private ChessGame chessGame;

    @BeforeEach
    public void setUp() {
        board = new Board();
        chessView = new ChessView(board);
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("보드에 폰을 추가할 수 있다")
    public void create() {
        chessGame.start();
        assertEquals(32, chessGame.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                chessView.showBoard());
    }

    @Test
    @DisplayName("체스판을 출력할 수 있다")
    public void showBoard() {
        chessGame.start();
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환한다")
    public void countPieces() {
        chessGame.start();
        assertEquals(8, chessGame.countPieces(Color.BLACK, Type.PAWN));
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회할 수 있다")
    public void findPiece() {
        chessGame.start();
        assertEquals(PieceFactory.createRook(Color.BLACK, new Position("a8")), board.findPiece("a8"));
        assertEquals(PieceFactory.createRook(Color.BLACK, new Position("h8")), board.findPiece("h8"));
        assertEquals(PieceFactory.createRook(Color.WHITE, new Position("a1")), board.findPiece("a1"));
        assertEquals(PieceFactory.createRook(Color.WHITE, new Position("h1")), board.findPiece("h1"));
    }

    @Test
    @DisplayName("임의의 기물을 체스판의 다른 위치로 이동할 수 있다")
    public void move() {
        chessGame.start();
        String sourcePosition = "b2";
        String targetPosition = "b3";
        chessGame.move(sourcePosition, targetPosition);
        //assertEquals(Piece.createBlank(new Position(sourcePosition)), board.findPiece(sourcePosition));
        //assertEquals(Piece.createWhitePawn(new Position(targetPosition)), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("남아있는 기물에 대한 점수 계산이 가능해야한다")
    public void caculcatePoint() {
        board.initialize();
        addPiece("b6", PieceFactory.createPawn(Color.BLACK, new Position("b6")));
        addPiece("e6", PieceFactory.createQueen(Color.BLACK, new Position("e6")));
        addPiece("b8", PieceFactory.createKing(Color.BLACK, new Position("b8")));
        addPiece("c8", PieceFactory.createRook(Color.BLACK, new Position("c8")));

        addPiece("f2", PieceFactory.createPawn(Color.WHITE, new Position("f2")));
        addPiece("g2", PieceFactory.createPawn(Color.WHITE, new Position("g2")));
        addPiece("e1", PieceFactory.createRook(Color.WHITE, new Position("e1")));
        addPiece("f1", PieceFactory.createKing(Color.WHITE, new Position("f1")));

        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);

        System.out.println(chessView.showBoard());
    }

    private void addPiece(final String position, final Piece piece) {
        chessGame.move(position, piece);
    }

    @Test
    @DisplayName("원하는 색상의 기물들을 정렬된 리스트로 받을 수 있어야 한다")
    public void getSortedPieces() {
        chessGame.start();
        List<Piece> sortedWhitePieces = board.getSortedPieces(Color.WHITE);
        System.out.println(sortedWhitePieces);
        System.out.println(sortedWhitePieces.reversed());
    }

}
