package chess;

import chess.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }
/*
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
    }*/
/*

    @Test
    public void initialize() throws Exception {
        board.initialize();
        assertEquals("♙♙♙♙♙♙♙♙", board.getWhitePawnsResult());
        assertEquals("♟♟♟♟♟♟♟♟", board.getBlackPawnsResult());
        board.print();
    }
*/
/*

    @Test
    void count() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        assertEquals(8, board.count(Piece.Color.WHITE, Pawn.class));
        assertEquals(8, board.count(Piece.Color.BLACK, Pawn.class));
        assertEquals(2, board.count(Piece.Color.WHITE, Rook.class));
        assertEquals(2, board.count(Piece.Color.BLACK, Rook.class));
    }

*/

    @Test
    void findPiece() throws Exception {
        board.initialize();

        assertTrue(board.findPiece("a8").isBlack());
        assertTrue(board.findPiece("a8") instanceof Rook);
        assertTrue(board.findPiece("h8").isBlack());
        assertTrue(board.findPiece("h8") instanceof Rook);
        assertTrue(board.findPiece("a1").isWhite());
        assertTrue(board.findPiece("a1") instanceof Rook);
        assertTrue(board.findPiece("h1").isWhite());
        assertTrue(board.findPiece("h1") instanceof Rook);
    }

    @Test
    void move() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Rook.createBlack();
        board.putPiece(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    void caculcatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Pawn.createBlack());
        addPiece("e6", Queen.createBlack());
        addPiece("b8", King.createBlack());
        addPiece("c8", Rook.createBlack());

        addPiece("f2", Pawn.createWhite());
        addPiece("g2", Pawn.createWhite());
        addPiece("g3", Pawn.createWhite());
        addPiece("e1", Rook.createWhite());
        addPiece("f1", King.createWhite());


        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);

        assertInstanceOf(King.class, board.sortByPointAsc().get(0));
        assertInstanceOf(Queen.class, board.sortByPointDesc().get(0));

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.putPiece(position, piece);
    }

    @Test
    public void move2() throws Exception {
        board.initialize();

        String sourcePosition;
        String targetPosition;
        board.print();
        sourcePosition = "b2";
        targetPosition = "b7";
        board.move(sourcePosition, targetPosition);
        board.print();
        sourcePosition = "b7";
        targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        board.print();
    }
}