package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import util.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void beforeEach()
    {
        board = new Board();
    }

    @Test
    public void countColorPiece(){
        // given
        board.addPiece(new Position("a1"), new Piece(PieceColor.BLACK, PieceType.PAWN));
        // when & then
        assertEquals(1, board.countPiece(PieceColor.BLACK, PieceType.PAWN));
    }

    @Test
    public void findPiece(){
        // given
        board.addPiece(new Position("a1"), new Piece(PieceColor.BLACK, PieceType.PAWN));
        // when & then
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("a1"));
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
    public void getSpecificColorPieces()
    {
        anySet();
        List<Piece> piecesAsc = board.getPieces(PieceColor.BLACK, Order.ASC);
        System.out.println(piecesAsc);

        List<Piece> piecesDesc = board.getPieces(PieceColor.BLACK, Order.DESC);
        System.out.println(piecesDesc);
    }

    private void anySet()
    {
        board.addPiece(new Position("b6"), new Piece(PieceColor.BLACK, PieceType.PAWN));
        board.addPiece(new Position("e6"), new Piece(PieceColor.BLACK, PieceType.QUEEN));
        board.addPiece(new Position("b8"), new Piece(PieceColor.BLACK, PieceType.KING));
        board.addPiece(new Position("c8"), new Piece(PieceColor.BLACK, PieceType.ROOK));

        board.addPiece(new Position("f2"), new Piece(PieceColor.WHITE, PieceType.PAWN));
        board.addPiece(new Position("g2"), new Piece(PieceColor.WHITE, PieceType.PAWN));
        board.addPiece(new Position("e1"), new Piece(PieceColor.WHITE, PieceType.ROOK));
        board.addPiece(new Position("f1"), new Piece(PieceColor.WHITE, PieceType.KING));

    }
}
