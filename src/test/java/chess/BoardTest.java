package chess;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static utils.StringUtils.appendNewLine;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.board.Position;
import chess.board.calculator.OrderBy;
import chess.pieces.PieceFactory;
import chess.pieces.type.Color;
import chess.pieces.Piece;
import chess.pieces.type.Representation;
import chess.pieces.type.Type;
import org.junit.jupiter.api.*;

import java.util.List;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = BoardFactory.createStandard();
    }

    @Test
    @DisplayName("체스판을 출력한다")
    void print() {
        String print = board.print();
        assertThat(print).hasSize(72);
        System.out.println(print);
    }

    @Test
    @DisplayName("전체 체스판의 상태를 확인한다")
    public void create() throws Exception {
        String blankRank = appendNewLine("........");

        assertEquals(32, board.pieceCount());
        assertEquals(
                appendNewLine("♜♞♝♛♚♝♞♜") +
                        appendNewLine("♟♟♟♟♟♟♟♟") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("♙♙♙♙♙♙♙♙") +
                        appendNewLine("♖♘♗♕♔♗♘♖"),
                board.print());
    }

    @Test
    @DisplayName("체스판 초기화 후 각 기물의 갯수를 확인한다")
    public void countPiece() {
        assertThat(board.pieceCount(Representation.BLACK_ROOK)).isEqualTo(2);
        assertThat(board.pieceCount(Representation.BLACK_KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Representation.BLACK_BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Representation.BLACK_QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Representation.BLACK_KING)).isEqualTo(1);
        assertThat(board.pieceCount(Representation.BLACK_PAWN)).isEqualTo(8);

        assertThat(board.pieceCount(Representation.WHITE_ROOK)).isEqualTo(2);
        assertThat(board.pieceCount(Representation.WHITE_KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Representation.WHITE_BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Representation.WHITE_QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Representation.WHITE_KING)).isEqualTo(1);
        assertThat(board.pieceCount(Representation.WHITE_PAWN)).isEqualTo(8);
    }

    @Test
    @DisplayName("체스판에서 특정 위치에 어떤 기물이 있는지 확인한다")
    public void findPiece() throws Exception {
        assertTrue(board.findPiece(Position.from("a8")).isPieceOf(Type.ROOK, Color.BLACK));
        assertTrue(board.findPiece(Position.from("h8")).isPieceOf(Type.ROOK, Color.BLACK));
        assertTrue(board.findPiece(Position.from("a1")).isPieceOf(Type.ROOK, Color.WHITE));
        assertTrue(board.findPiece(Position.from("h1")).isPieceOf(Type.ROOK, Color.WHITE));
    }

    @Test
    @DisplayName("임의의 기물을 체스판에 추가한다")
    public void move() throws Exception {
        board = BoardFactory.createEmpty();

        Position position = Position.from("b5");
        Piece piece = PieceFactory.createBlackRook(position);
        board.setPiece(piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.print());
    }

    @Test
    @DisplayName("일부 점수를 계산한다")
    public void getScore() throws Exception {
        board = BoardFactory.createEmpty();

        board.setPiece(PieceFactory.createBlackRook(Position.from("b5")));
        board.setPiece(PieceFactory.createWhiteKnight(Position.from("a4")));

        double b = board.getScore(Color.BLACK);
        double w = board.getScore(Color.WHITE);

        assertThat(b).isEqualTo(5.0);
        assertThat(w).isEqualTo(2.5);
    }

    @Test
    @DisplayName("초기 체스판에서 점수를 계산한다")
    public void getScoreAll() throws Exception {
        double b = board.getScore(Color.BLACK);
        double w = board.getScore(Color.WHITE);

        assertThat(b).isEqualTo(38.0);
        assertThat(w).isEqualTo(38.0);
    }

    @Test
    @DisplayName("세팅된 체스판에서 점수를 계산한다")
    public void getScoreAll2() throws Exception {
        board.setPiece(PieceFactory.createBlank(Position.C2));

        double b = board.getScore(Color.BLACK);
        double w = board.getScore(Color.WHITE);

        assertThat(b).isEqualTo(38.0);
        assertThat(w).isEqualTo(37.0);
    }

    @Test
    @DisplayName("같은 열에 폰이 2개일 때 점수를 계산한다")
    public void getScoreCase() throws Exception {
        board = BoardFactory.createEmpty();

        board.setPiece(PieceFactory.createBlackPawn(Position.from("a1")));
        board.setPiece(PieceFactory.createBlackPawn(Position.from("a2")));
        board.setPiece(PieceFactory.createBlackPawn(Position.from("a3")));

        double b = board.getScore(Color.BLACK);
        double w = board.getScore(Color.WHITE);

        assertThat(b).isEqualTo(1.5);
        assertThat(w).isEqualTo(0.0);
    }

    @Test
    @DisplayName("특정상황에서 체스판에 점수를 계산한다")
    public void caculcatePoint() throws Exception {
        board = BoardFactory.createEmpty();

        board.setPiece(PieceFactory.createBlackPawn(Position.from("b6")));
        board.setPiece(PieceFactory.createBlackQueen(Position.from("e6")));
        board.setPiece(PieceFactory.createBlackKing(Position.from("b8")));
        board.setPiece(PieceFactory.createBlackRook(Position.from("c8")));
        board.setPiece(PieceFactory.createWhitePawn(Position.from("f2")));
        board.setPiece(PieceFactory.createWhitePawn(Position.from("g2")));
        board.setPiece(PieceFactory.createWhiteRook(Position.from("e1")));
        board.setPiece(PieceFactory.createWhiteKing(Position.from("f1")));

        assertEquals(15.0, board.getScore(Color.BLACK), 0.01);
        assertEquals(7.0, board.getScore(Color.WHITE), 0.01);

        System.out.println(board.print());
    }


    @Test
    @DisplayName("기물의 점수순으로 정렬한다")
    public void sort() throws Exception {
        List<Piece> whites = board.sortByScore(Color.WHITE, OrderBy.DESC);
        List<Piece> blacks = board.sortByScore(Color.BLACK, OrderBy.ASC);

        Piece whitePiece1 = whites.get(0);
        Piece whitePiece2 = whites.get(1);

        assertEquals(Type.QUEEN, whitePiece1.getType());
        assertEquals(Color.WHITE, whitePiece1.getColor());
        assertEquals(Type.ROOK, whitePiece2.getType());
        assertEquals(Color.WHITE, whitePiece2.getColor());


        Piece blackPiece1 = blacks.get(0);
        Piece blackPiece2 = blacks.get(1);

        assertEquals(Type.KING, blackPiece1.getType());
        assertEquals(Color.BLACK, blackPiece1.getColor());
        assertEquals(Type.PAWN, blackPiece2.getType());
        assertEquals(Color.BLACK, blackPiece2.getColor());
    }

    @Test
    @DisplayName("특정 기물이 존재하는지 확인한다")
    public void test_exists_piece() {
        board = BoardFactory.createEmpty();

        board.setPiece(PieceFactory.createBlackKing(Position.A8));
        boolean bk = board.existsPiece(Representation.BLACK_KING);
        boolean wk = board.existsPiece(Representation.WHITE_KING);

        assertTrue(bk);
        assertFalse(wk);
    }
}
