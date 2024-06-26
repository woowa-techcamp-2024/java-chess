package chess;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Representations;
import org.junit.jupiter.api.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 출력한다")
    void print() {
        board.initialize();

        String print = board.print();
        assertThat(print).hasSize(72);
        System.out.println(print);
    }

    @Test
    @DisplayName("전체 체스판의 상태를 확인한다")
    public void create() throws Exception {
        board.initialize();
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
        board.initialize();

        assertThat(board.pieceCount(Representations.BLACK_ROOK)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.BLACK_KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.BLACK_BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.BLACK_QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.BLACK_KING)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.BLACK_PAWN)).isEqualTo(8);

        assertThat(board.pieceCount(Representations.WHITE_ROOK)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.WHITE_KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.WHITE_BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Representations.WHITE_QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.WHITE_KING)).isEqualTo(1);
        assertThat(board.pieceCount(Representations.WHITE_PAWN)).isEqualTo(8);
    }

    @Test
    @DisplayName("체스판에서 특정 위치에 어떤 기물이 있는지 확인한다")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.create(Representations.Type.ROOK, Color.BLACK), board.findPiece("a8"));
        assertEquals(Piece.create(Representations.Type.ROOK, Color.BLACK), board.findPiece("h8"));
        assertEquals(Piece.create(Representations.Type.ROOK, Color.WHITE), board.findPiece("a1"));
        assertEquals(Piece.create(Representations.Type.ROOK, Color.WHITE), board.findPiece("h1"));
    }

    @Test
    @DisplayName("임의의 기물을 체스판에 추가한다")
    public void move() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.create(Representations.Type.ROOK, Color.BLACK);
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.print());
    }

    @Test
    @DisplayName("일부 점수를 계산한다")
    public void getScore() throws Exception {
        board.initializeEmpty();
        board.move("b5", Piece.create(Representations.Type.ROOK, Color.BLACK));
        board.move("a4", Piece.create(Representations.Type.KNIGHT, Color.WHITE));

        double b = board.getScore(Color.BLACK);
        double w = board.getScore(Color.WHITE);

        assertThat(b).isEqualTo(5.0);
        assertThat(w).isEqualTo(2.5);
    }

    @Test
    @DisplayName("초기 체스판에서 점수를 계산한다")
    public void getScoreAll() throws Exception {
        board.initialize();

        double b = board.getScore(Color.BLACK);
        double w = board.getScore(Color.WHITE);

        assertThat(b).isEqualTo(38.0);
        assertThat(w).isEqualTo(38.0);
    }

    @Test
    @DisplayName("같은 열에 폰이 2개일 때 점수를 계산한다")
    public void getScoreCase() throws Exception {
        board.initializeEmpty();

        board.move("a1", Piece.create(Representations.Type.PAWN, Color.BLACK));
        board.move("a2", Piece.create(Representations.Type.PAWN, Color.BLACK));
        board.move("a3", Piece.create(Representations.Type.PAWN, Color.BLACK));

        double b = board.getScore(Color.BLACK);
        double w = board.getScore(Color.WHITE);

        assertThat(b).isEqualTo(1.5);
        assertThat(w).isEqualTo(0.0);
    }

    @Test
    @DisplayName("특정상황에서 체스판에 점수를 계산한다")
    public void caculcatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.create(Representations.Type.PAWN, Color.BLACK));
        addPiece("e6", Piece.create(Representations.Type.QUEEN, Color.BLACK));
        addPiece("b8", Piece.create(Representations.Type.KING, Color.BLACK));
        addPiece("c8", Piece.create(Representations.Type.ROOK, Color.BLACK));

        addPiece("f2", Piece.create(Representations.Type.PAWN, Color.WHITE));
        addPiece("g2", Piece.create(Representations.Type.PAWN, Color.WHITE));
        addPiece("e1", Piece.create(Representations.Type.ROOK, Color.WHITE));
        addPiece("f1", Piece.create(Representations.Type.KING, Color.WHITE));

        assertEquals(15.0, board.getScore(Color.BLACK), 0.01);
        assertEquals(7.0, board.getScore(Color.WHITE), 0.01);

        System.out.println(board.print());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
