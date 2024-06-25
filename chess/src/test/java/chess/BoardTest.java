package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;
import pieces.PieceUnicode;
import util.Order;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private final int NUM_COL = 8;
    private final String BLANK = ".";
    private Board board;

    @BeforeEach
    public void beforeAll()
    {
        board = new Board();
        board.initialize();
    }

    @Test
    public void initialize() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb
                .append(generateBlackInitLine()).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(BLANK.repeat(NUM_COL)).append("\n")
                .append(generateWhiteInitLine());

        assertEquals(sb.toString().strip(), board.show());
        assertEquals(NUM_COL*4, board.getNumOfPieces());
    }

    private String generateBlackInitLine() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(PieceUnicode.BLACK_ROOK.getUnicode())
                .append(PieceUnicode.BLACK_KNIGHT.getUnicode())
                .append(PieceUnicode.BLACK_BISHOP.getUnicode())
                .append(PieceUnicode.BLACK_QUEEN.getUnicode())
                .append(PieceUnicode.BLACK_KING.getUnicode())
                .append(PieceUnicode.BLACK_BISHOP.getUnicode())
                .append(PieceUnicode.BLACK_KNIGHT.getUnicode())
                .append(PieceUnicode.BLACK_ROOK.getUnicode())
                .append("\n")
                .append(PieceUnicode.BLACK_PAWN.getUnicode().repeat(NUM_COL));
        return sb.toString();
    }

    private String generateWhiteInitLine() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(PieceUnicode.WHITE_PAWN.getUnicode().repeat(NUM_COL))
                .append("\n")
                .append(PieceUnicode.WHITE_ROOK.getUnicode())
                .append(PieceUnicode.WHITE_KNIGHT.getUnicode())
                .append(PieceUnicode.WHITE_BISHOP.getUnicode())
                .append(PieceUnicode.WHITE_QUEEN.getUnicode())
                .append(PieceUnicode.WHITE_KING.getUnicode())
                .append(PieceUnicode.WHITE_BISHOP.getUnicode())
                .append(PieceUnicode.WHITE_KNIGHT.getUnicode())
                .append(PieceUnicode.WHITE_ROOK.getUnicode());
        return sb.toString();
    }

    @Test
    public void countColorPiece(){
        assertEquals(NUM_COL, board.countPiece(PieceColor.BLACK, PieceType.PAWN));
        assertEquals(2, board.countPiece(PieceColor.BLACK, PieceType.ROOK));
        assertEquals(2, board.countPiece(PieceColor.BLACK, PieceType.KNIGHT));
        assertEquals(2, board.countPiece(PieceColor.BLACK, PieceType.BISHOP));
        assertEquals(1, board.countPiece(PieceColor.BLACK, PieceType.QUEEN));
        assertEquals(1, board.countPiece(PieceColor.BLACK, PieceType.KING));

        assertEquals(NUM_COL, board.countPiece(PieceColor.WHITE, PieceType.PAWN));
        assertEquals(2, board.countPiece(PieceColor.WHITE, PieceType.ROOK));
        assertEquals(2, board.countPiece(PieceColor.WHITE, PieceType.KNIGHT));
        assertEquals(2, board.countPiece(PieceColor.WHITE, PieceType.BISHOP));
        assertEquals(1, board.countPiece(PieceColor.WHITE, PieceType.QUEEN));
        assertEquals(1, board.countPiece(PieceColor.WHITE, PieceType.KING));
    }

    @Test
    public void findPiece(){
        assertEquals(new Piece(PieceColor.BLACK, PieceType.ROOK), board.findPiece("a8"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.KNIGHT), board.findPiece("b8"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.BISHOP), board.findPiece("c8"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.QUEEN), board.findPiece("d8"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.KING), board.findPiece("e8"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.BISHOP), board.findPiece("f8"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.KNIGHT), board.findPiece("g8"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.ROOK), board.findPiece("h8"));

        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("a7"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("b7"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("c7"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("d7"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("e7"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("f7"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("g7"));
        assertEquals(new Piece(PieceColor.BLACK, PieceType.PAWN), board.findPiece("h7"));

        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("a2"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("b2"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("c2"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("d2"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("e2"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("f2"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("g2"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.PAWN), board.findPiece("h2"));

        assertEquals(new Piece(PieceColor.WHITE, PieceType.ROOK), board.findPiece("a1"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.KNIGHT), board.findPiece("b1"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.BISHOP), board.findPiece("c1"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.QUEEN), board.findPiece("d1"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.KING), board.findPiece("e1"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.BISHOP), board.findPiece("f1"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.KNIGHT), board.findPiece("g1"));
        assertEquals(new Piece(PieceColor.WHITE, PieceType.ROOK), board.findPiece("h1"));
    }

    @Test
    public void move()
    {
        board = new Board();
        Piece whiteBishop = new Piece(PieceColor.WHITE, PieceType.BISHOP);
        board.move("b5", whiteBishop);
        assertEquals(whiteBishop, board.findPiece("b5"));
    }

    @Test
    public void calculatePoint() throws Exception
    {
        board = new Board();

        addPiece("b6", new Piece(PieceColor.BLACK, PieceType.PAWN));
        addPiece("e6", new Piece(PieceColor.BLACK, PieceType.QUEEN));
        addPiece("b8", new Piece(PieceColor.BLACK, PieceType.KING));
        addPiece("c8", new Piece(PieceColor.BLACK, PieceType.ROOK));

        addPiece("f2", new Piece(PieceColor.WHITE, PieceType.PAWN));
        addPiece("g2", new Piece(PieceColor.WHITE, PieceType.PAWN));
        addPiece("e1", new Piece(PieceColor.WHITE, PieceType.ROOK));
        addPiece("f1", new Piece(PieceColor.WHITE, PieceType.KING));

        System.out.println(board.show());

        assertEquals(15.0, board.calculatePoint(PieceColor.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(PieceColor.WHITE), 0.01);

    }

    private void addPiece(String position, Piece piece){
        board.move(position, piece);
    }

    @Test
    public void getSpecificColorPieces()
    {
        List<Piece> piecesAsc = board.getPieces(PieceColor.BLACK, Order.ASC);
        System.out.println(piecesAsc);

        List<Piece> piecesDesc = board.getPieces(PieceColor.BLACK, Order.DESC);
        System.out.println(piecesDesc);
    }
}
