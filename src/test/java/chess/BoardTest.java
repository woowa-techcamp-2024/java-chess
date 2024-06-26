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
}
