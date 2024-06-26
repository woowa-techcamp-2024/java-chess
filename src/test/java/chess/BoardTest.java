package chess;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환")
    void findPiece() {
        board.initialize();

        assertEquals(8, board.findPiece(Color.BLACK, Type.PAWN));
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회")
    void findPiece_by_pos() {
        board.initialize();

        assertEquals(Piece.createBlack(Type.ROOK), board.findPiece("a8"));
        assertEquals(Piece.createBlack(Type.ROOK), board.findPiece("h8"));
        assertEquals(Piece.createWhite(Type.ROOK), board.findPiece("a1"));
        assertEquals(Piece.createWhite(Type.ROOK), board.findPiece("h1"));

    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가")
    void addPiece_by_pos() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlack(Type.ROOK);
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("체스 프로그램 점수 계산하기")
    void calcScore() {
        board.initializeEmpty();

        board.move("b6", Piece.createBlack(Type.PAWN));
        board.move("e6", Piece.createBlack(Type.QUEEN));
        board.move("b8", Piece.createBlack(Type.KING));
        board.move("c8", Piece.createBlack(Type.ROOK));

        board.move("f2", Piece.createWhite(Type.PAWN));
        board.move("g2", Piece.createWhite(Type.PAWN));
        board.move("e1", Piece.createWhite(Type.ROOK));
        board.move("f1", Piece.createWhite(Type.KING));

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());


    }

    @Test
    @DisplayName("기물의 점수가 높은 순으로 정렬")
    void sortByScore() {
        board.initialize();
        List<Piece> whitePieces = new ArrayList<>(board.getWhitePieces());
        whitePieces.sort(Comparator.comparing(Piece::getDefaultScore).reversed());
        for (Piece whitePiece : whitePieces) {

            System.out.println(whitePiece.getDefaultScore());
        }
    }


    @Test
    @DisplayName("기물의 이동 구현")
    public void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        assertEquals(Piece.createBlank(new Position(sourcePosition)), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhite(Type.PAWN, new Position(targetPosition)), board.findPiece(targetPosition));
    }
}
