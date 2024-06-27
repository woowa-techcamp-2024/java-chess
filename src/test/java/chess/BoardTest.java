package chess;

import chess.pieces.ChessPiece;
import chess.pieces.PieceFactory;
import chess.pieces.PieceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static chess.pieces.PieceTypes.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    private PieceCreator pieceCreator;
    @BeforeEach
    void boardInit(){
        pieceCreator = new PieceCreatorWithFactory();
        board = new Board(pieceCreator);
    }
    @Test
    public void create() throws Exception {
        ChessPiece white = PieceFactory.createPiece(PieceTypes.WHITE_PAWN);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPiece(0));

        ChessPiece black = PieceFactory.createPiece(PieceTypes.BLACK_PAWN);
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
                """.replaceAll("\n",System.lineSeparator()),board.print());
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
        Optional<ChessPiece> a8 = board.findPiece("a8");
        Optional<ChessPiece> h8 = board.findPiece("h8");
        Optional<ChessPiece> a1 = board.findPiece("a1");
        Optional<ChessPiece> h1 = board.findPiece("h1");

        assertTrue(a8.isPresent());
        assertTrue(h8.isPresent());
        assertTrue(a1.isPresent());
        assertTrue(h1.isPresent());


        //then=
        verifyPieceColorAndType(a8.get(),BLACK_ROOK);
        verifyPieceColorAndType(h8.get(),BLACK_ROOK);
        verifyPieceColorAndType(a1.get(),WHITE_ROOK);
        verifyPieceColorAndType(h1.get(),WHITE_ROOK);
    }

    private void verifyPieceColorAndType(ChessPiece chessPiece, PieceTypes pieceType){
        assertEquals(pieceType.getColor(), chessPiece.getColor());
        assertEquals(pieceType.getType(), chessPiece.getType());
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
    public void calculcatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createPiece(BLACK_PAWN));
        addPiece("e6", PieceFactory.createPiece(BLACK_QUEEN));
        addPiece("b8", PieceFactory.createPiece(BLACK_KING));
        addPiece("c8", PieceFactory.createPiece(BLACK_ROOK));

        addPiece("f2", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("g2", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("e1", chess.pieces.PieceFactory.createPiece(WHITE_ROOK));
        addPiece("f1", PieceFactory.createPiece(WHITE_KING));

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);
    }

    @Test
    public void calculatePointTest2_verticalPawns(){
        board.initializeEmpty();

        addPiece("e1", PieceFactory.createPiece(WHITE_ROOK));
        addPiece("f1", PieceFactory.createPiece(WHITE_KING));
        addPiece("f2", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("g2", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("f3", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("h3", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("f4", PieceFactory.createPiece(WHITE_KNIGHT));
        addPiece("g4", PieceFactory.createPiece(WHITE_QUEEN));

        addPiece("b6", PieceFactory.createPiece(BLACK_PAWN));
        addPiece("e6", PieceFactory.createPiece(BLACK_QUEEN));
        addPiece("a7", PieceFactory.createPiece(BLACK_PAWN));
        addPiece("c7", PieceFactory.createPiece(BLACK_PAWN));
        addPiece("d7", PieceFactory.createPiece(BLACK_BISHOP));
        addPiece("b8", PieceFactory.createPiece(BLACK_KING));
        addPiece("c8", PieceFactory.createPiece(BLACK_ROOK));

        assertEquals(20.0,board.calculatePoint(Color.BLACK),0.01);
        assertEquals(19.5,board.calculatePoint(Color.WHITE),0.01);
    }

    @Test
    public void sortEachColorOrderByPointDesc(){
        board.initializeEmpty();
        addPiece("h3", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("f4", PieceFactory.createPiece(WHITE_KNIGHT));

        addPiece("b6", PieceFactory.createPiece(BLACK_PAWN));
        addPiece("e6", PieceFactory.createPiece(BLACK_QUEEN));

        List<ChessPiece> blackOrderByPointDesc = board.getPieceOrderByPoint(Color.BLACK,false);
        assertEquals(2,blackOrderByPointDesc.size());
        assertEquals(Type.QUEEN,blackOrderByPointDesc.get(0).getType());
        assertEquals(Color.BLACK,blackOrderByPointDesc.get(0).getColor());

        assertEquals(Type.PAWN,blackOrderByPointDesc.get(1).getType());
        assertEquals(Color.BLACK,blackOrderByPointDesc.get(1).getColor());

        List<ChessPiece> whiteOrderByPointDesc = board.getPieceOrderByPoint(Color.WHITE,false);
        assertEquals(2,whiteOrderByPointDesc.size());
        assertEquals(Type.KNIGHT,whiteOrderByPointDesc.get(0).getType());
        assertEquals(Color.WHITE,whiteOrderByPointDesc.get(0).getColor());

        assertEquals(Type.PAWN,whiteOrderByPointDesc.get(1).getType());
        assertEquals(Color.WHITE,whiteOrderByPointDesc.get(1).getColor());
    }

    @Test
    public void sortEachColorOrderByPointAsc(){
        board.initializeEmpty();
        addPiece("h3", PieceFactory.createPiece(WHITE_PAWN));
        addPiece("f4", PieceFactory.createPiece(WHITE_KNIGHT));

        addPiece("b6", PieceFactory.createPiece(BLACK_PAWN));
        addPiece("e6", PieceFactory.createPiece(BLACK_QUEEN));

        List<ChessPiece> blackOrderByPointAsc = board.getPieceOrderByPoint(Color.BLACK,true);
        assertEquals(2,blackOrderByPointAsc.size());
        assertEquals(Type.PAWN,blackOrderByPointAsc.get(0).getType());
        assertEquals(Color.BLACK,blackOrderByPointAsc.get(0).getColor());

        assertEquals(Type.QUEEN,blackOrderByPointAsc.get(1).getType());
        assertEquals(Color.BLACK,blackOrderByPointAsc.get(1).getColor());

        List<ChessPiece> whiteOrderByPointAsc = board.getPieceOrderByPoint(Color.WHITE,true);
        assertEquals(2,whiteOrderByPointAsc.size());
        assertEquals(Type.PAWN,whiteOrderByPointAsc.get(0).getType());
        assertEquals(Color.WHITE,whiteOrderByPointAsc.get(0).getColor());

        assertEquals(Type.KNIGHT,whiteOrderByPointAsc.get(1).getType());
        assertEquals(Color.WHITE,whiteOrderByPointAsc.get(1).getColor());
    }

    private void addPiece(String position, ChessPiece chessPiece) {
        board.move(position, chessPiece);
    }

    @Test
    public void move() throws Exception{
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        boolean moved = board.move(sourcePosition,targetPosition);

        assertTrue(moved);
        verifyPieceWithPosition(sourcePosition,NO_PIECE);
        verifyPieceWithPosition(targetPosition,WHITE_PAWN);
    }

    @Test
    public void moveOutOfRange(){
        board.initialize();

        String sourcePosition = "b2";
        String targetPositionOutOfRange = "A1";
        boolean moved = board.move(sourcePosition, targetPositionOutOfRange);
        assertFalse(moved);

        targetPositionOutOfRange = "i8";
        assertFalse(board.move(sourcePosition,targetPositionOutOfRange));

        targetPositionOutOfRange = "a10";
        assertFalse(board.move(sourcePosition, targetPositionOutOfRange));
    }

    void verifyPieceWithPosition(String position,PieceTypes type){
        Optional<ChessPiece> chessPiece = board.findPiece(position);

        assertTrue(chessPiece.isPresent());

        ChessPiece cp = chessPiece.get();
        assertEquals(type.getType(), cp.getType());
        assertEquals(type.getColor(), cp.getColor());
    }
}
