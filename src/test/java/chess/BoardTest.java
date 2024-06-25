package chess;

import chess.pieces.Piece;
import chess.pieces.PieceTypes;
import chess.pieces.PieceTypes.Color;
import chess.pieces.PieceTypes.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;
    @BeforeEach
    void boardInit(){
        board = new Board();
    }
    @Test
    public void create() throws Exception {
        Piece white = Piece.createPiece(PieceTypes.WHITE_PAWN);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPiece(0));

        Piece black = Piece.createPiece(PieceTypes.BLACK_PAWN);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPiece(1));
    }

    @Test
    public void print(){
        board.initialize();
        assertEquals(32,board.size());
        assertEquals("""
                RNBQKBNR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rnbqkbnr
                """,board.print());
    }

    @Test
    public void findPieceWithColorAndTypeTest(){
        board.initialize();

        assertEquals(8,board.findPieceWithColorAndType(Color.BLACK, Type.PAWN));
        assertEquals(8,board.findPieceWithColorAndType(Color.WHITE, Type.PAWN));
        assertEquals(2,board.findPieceWithColorAndType(Color.BLACK,Type.KNIGHT));
        assertEquals(2,board.findPieceWithColorAndType(Color.WHITE,Type.KNIGHT));
        assertEquals(2,board.findPieceWithColorAndType(Color.BLACK,Type.ROOK));
        assertEquals(2,board.findPieceWithColorAndType(Color.WHITE,Type.ROOK));
        assertEquals(2,board.findPieceWithColorAndType(Color.BLACK,Type.BISHOP));
        assertEquals(2,board.findPieceWithColorAndType(Color.WHITE,Type.BISHOP));
        assertEquals(1,board.findPieceWithColorAndType(Color.BLACK,Type.QUEEN));
        assertEquals(1,board.findPieceWithColorAndType(Color.WHITE,Type.QUEEN));
        assertEquals(1,board.findPieceWithColorAndType(Color.BLACK,Type.KING));
        assertEquals(1,board.findPieceWithColorAndType(Color.WHITE,Type.KING));

    }
}
