package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.*;
import util.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void beforeEach() throws Exception {
        board = new Board();
    }

    @Test
    public void countColorPiece() throws Exception {
        // given
        board.setPiece(new Position("a1"), new Pawn(PieceColor.BLACK, PieceType.PAWN, new Position("a1")));
        // when & then
        assertEquals(1, board.countPiece(PieceColor.BLACK, PieceType.PAWN));
    }

    @Test
    public void findPiece() throws Exception {
        // given
        board.setPiece(new Position("a1"), new Pawn(PieceColor.BLACK, PieceType.PAWN, new Position("a1")));
        // when & then
        assertEquals(new Pawn(PieceColor.BLACK, PieceType.PAWN, new Position("a1")), board.findPiece(new Position("a1")));
    }

    @Test
    public void calculatePoint() throws Exception
    {
        anySet();
        System.out.println(board.show());

        assertEquals(15.0, board.calculatePoint(PieceColor.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(PieceColor.WHITE), 0.01);
    }

    @Test
    public void getSpecificColorPieces() throws Exception {
        anySet();
        List<Piece> piecesAsc = board.getPieces(PieceColor.BLACK, Order.ASC);
        System.out.println(piecesAsc);

        List<Piece> piecesDesc = board.getPieces(PieceColor.BLACK, Order.DESC);
        System.out.println(piecesDesc);
    }

    private void anySet() throws Exception {
        board.setPiece(new Position("b6"), new Pawn(PieceColor.BLACK, PieceType.PAWN, new Position("b6")));
        board.setPiece(new Position("e6"), new Queen(PieceColor.BLACK, PieceType.QUEEN, new Position("e6")));
        board.setPiece(new Position("b8"), new King(PieceColor.BLACK, PieceType.KING, new Position("b8")));
        board.setPiece(new Position("c8"), new Rook(PieceColor.BLACK, PieceType.ROOK, new Position("c8")));

        board.setPiece(new Position("f2"), new Pawn(PieceColor.WHITE, PieceType.PAWN, new Position("f2")));
        board.setPiece(new Position("g2"), new Pawn(PieceColor.WHITE, PieceType.PAWN, new Position("g2")));
        board.setPiece(new Position("e1"), new Rook(PieceColor.WHITE, PieceType.ROOK, new Position("e1")));
        board.setPiece(new Position("f1"), new King(PieceColor.WHITE, PieceType.KING, new Position("f1")));
    }
}
