package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Piece;
import chess.pieces.PieceTypes;
import chess.pieces.PieceTypes.Color;
import chess.pieces.PieceTypes.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static chess.pieces.PieceTypes.*;
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
        assertEquals(32,board.pieceCount());
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

    @Test
    public void findPieceWithPositionTest(){
        //given
        board.initialize();

        //when
        ChessPiece a8 = board.findPiece("a8");
        ChessPiece h8 = board.findPiece("h8");
        ChessPiece a1 = board.findPiece("a1");
        ChessPiece h1 = board.findPiece("h1");

        //then=
        verifyPieceColorAndType(a8,BLACK_ROOK);
        verifyPieceColorAndType(h8,BLACK_ROOK);
        verifyPieceColorAndType(a1,WHITE_ROOK);
        verifyPieceColorAndType(h1,WHITE_ROOK);
    }

    private void verifyPieceColorAndType(ChessPiece piece, PieceTypes pieceType){
        assertEquals(pieceType.getColor(),piece.getColor());
        assertEquals(pieceType.getType(),piece.getType());
    }

    @Test
    public void initializeEmptytest() throws Exception{
        //given

        //when
        board.initializeEmpty();

        //then
        assertEquals(0,board.pieceCount());
    }

    @Test
    public void caculcatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createPiece(BLACK_PAWN));
        addPiece("e6", Piece.createPiece(BLACK_QUEEN));
        addPiece("b8", Piece.createPiece(BLACK_KING));
        addPiece("c8", Piece.createPiece(BLACK_ROOK));

        addPiece("f2", Piece.createPiece(WHITE_PAWN));
        addPiece("g2", Piece.createPiece(WHITE_PAWN));
        addPiece("e1", Piece.createPiece(WHITE_ROOK));
        addPiece("f1", Piece.createPiece(WHITE_KING));

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.print());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
