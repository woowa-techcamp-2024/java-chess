package chess;

import chess.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void create() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());
        assertEquals(
                "\ta\tb\tc\td\te\tf\tg\th\n" +
                        "8\t♜\t♞\t♝\t♛\t♚\t♝\t♞\t♜\t8\n" +
                        "7\t♟\t♟\t♟\t♟\t♟\t♟\t♟\t♟\t7\n" +
                        "6\t.\t.\t.\t.\t.\t.\t.\t.\t6\n" +
                        "5\t.\t.\t.\t.\t.\t.\t.\t.\t5\n" +
                        "4\t.\t.\t.\t.\t.\t.\t.\t.\t4\n" +
                        "3\t.\t.\t.\t.\t.\t.\t.\t.\t3\n" +
                        "2\t♙\t♙\t♙\t♙\t♙\t♙\t♙\t♙\t2\n" +
                        "1\t♖\t♘\t♗\t♕\t♔\t♗\t♘\t♖\t1\n" +
                        "\ta\tb\tc\td\te\tf\tg\th",
                board.showBoard());
    }

    @Test
    public void initialize() throws Exception {
        board.initialize();
        assertEquals("♙♙♙♙♙♙♙♙", board.getWhitePawnsResult());
        assertEquals("♟♟♟♟♟♟♟♟", board.getBlackPawnsResult());
        board.print();
    }
}