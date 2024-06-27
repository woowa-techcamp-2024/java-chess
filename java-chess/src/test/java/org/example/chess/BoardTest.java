package org.example.chess;

import org.example.chess.pieces.*;
import org.example.chess.pieces.global.Order;
import org.example.chess.pieces.global.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private static Piece black;
    private static Piece white;
    private Board board;

    @Test
    public void board_add_then_findPawn() {
        Board board = new Board();
        Position pos1 = Position.of(0, 0);
        board.setPiece(pos1, white);
        assertEquals(1, board.countPieces());
        assertEquals(white, board.findPiece(pos1));
    }

    @Test
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();

        assertEquals("pppppppp", board.getWhitePawnsRepresentation());
        assertEquals("PPPPPPPP", board.getBlackPawnsRepresentation());
    }

    @Test
    public void create() throws Exception {
        board.initialize();
        assertEquals(32, board.countPieces());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Rook.of(Piece.Color.BLACK), board.findPiece(Position.of("a8")));
        assertEquals(Rook.of(Piece.Color.BLACK), board.findPiece(Position.of("h8")));
        assertEquals(Rook.of(Piece.Color.WHITE), board.findPiece(Position.of("a1")));
        assertEquals(Rook.of(Piece.Color.WHITE), board.findPiece(Position.of("h1")));
    }

    @Test
    public void place() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Rook.of(Piece.Color.BLACK);
        board.setPiece(Position.of(position), piece);

        assertEquals(piece, board.findPiece(Position.of(position)));
        System.out.println(board.showBoard());
    }

    @Test
    public void calculatePoint_같은_열에_Pawn_중복_X() throws Exception {
        board.initializeEmpty();

        board.setPiece(Position.of("b6"), Pawn.of(Piece.Color.BLACK));
        board.setPiece( Position.of("e6"), Queen.of(Piece.Color.BLACK));
        board.setPiece( Position.of("b8"), King.of(Piece.Color.BLACK));
        board.setPiece( Position.of("c8"), Rook.of(Piece.Color.BLACK));

        board.setPiece( Position.of("f2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("g2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("e1"), Rook.of(Piece.Color.WHITE));
        board.setPiece( Position.of("f1"), King.of(Piece.Color.WHITE));

//        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
//        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void calculatePoint_같은_열에_Pawn_중복_O() throws Exception {
        board.initializeEmpty();

        board.setPiece(Position.of("b8"), King.of(Piece.Color.BLACK));
        board.setPiece(Position.of("c8"), Rook.of(Piece.Color.BLACK));
        board.setPiece(Position.of("a7"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("c7"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("d7"), Bishop.of(Piece.Color.BLACK));
        board.setPiece(Position.of("b6"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("e6"), Queen.of(Piece.Color.BLACK));

        board.setPiece( Position.of("f4"),Knight.of(Piece.Color.WHITE));
        board.setPiece( Position.of("g4"), Queen.of(Piece.Color.WHITE));
        board.setPiece( Position.of("f3"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("h3"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("f2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("g2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("e1"), Rook.of(Piece.Color.WHITE));
        board.setPiece( Position.of("f1"), King.of(Piece.Color.WHITE));

//        board.showScore();

//        assertEquals(20.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
//        assertEquals(19.5, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void board_sort_by_ascending() {
        board.initializeEmpty();

        board.setPiece( Position.of("b6"), Pawn.of(Piece.Color.BLACK));
        board.setPiece( Position.of("e6"), Queen.of(Piece.Color.BLACK));
        board.setPiece( Position.of("b8"), King.of(Piece.Color.BLACK));
        board.setPiece( Position.of("c8"), Rook.of(Piece.Color.BLACK));

        board.setPiece( Position.of("f2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("g2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece( Position.of("e1"), Rook.of(Piece.Color.WHITE));
        board.setPiece( Position.of("f1"), King.of(Piece.Color.WHITE));

        System.out.println(board.sort(Order.ASC));
    }

    @Test
    public void moveTo_성공() throws Exception {
        board.initialize();

        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("b3");

        board.moveTo(sourcePosition, targetPosition);

        assertEquals(NoPiece.of(), board.findPiece(sourcePosition));
        assertEquals(Pawn.of(Piece.Color.WHITE), board.findPiece(targetPosition));
    }

    @Test
    public void moveTo_목적지에_이미_같은팀_말이_존재해서_실패한다() throws Exception {
        board.initialize();

        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("c2");

        assertThrows(RuntimeException.class, () -> board.moveTo(sourcePosition, targetPosition));
    }

    @Test
    public void moveTo_테스트_출발지_자리에_말이_없어서_실패한다() throws Exception {
        board.initializeEmpty();

        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("b3");

        assertThrows(RuntimeException.class, () -> board.moveTo(sourcePosition, targetPosition));
    }

    @Test
    public void moveTo_성공_테스트_목적지에_다른팀이_존재한다() throws Exception {
        board.initialize();

        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("b7");

        board.moveTo(sourcePosition, targetPosition);

        board.showBoard();
        assertEquals(NoPiece.of(), board.findPiece(sourcePosition));
        assertEquals(Pawn.of(Piece.Color.WHITE), board.findPiece(targetPosition));
    }

    @BeforeEach
    public void set() {
        white = Pawn.of(Piece.Color.WHITE);
        black = Pawn.of(Piece.Color.BLACK);
        board = new Board();
    }
}