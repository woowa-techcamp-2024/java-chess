package org.example.chess;

import org.example.chess.pieces.Piece;
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
        board.place(white, pos1);
        assertEquals(1, board.pieceCount());
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
        assertEquals(32, board.pieceCount());
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

        assertEquals(Piece.createBlackRook(), board.findPiece(Position.of("a8")));
        assertEquals(Piece.createBlackRook(), board.findPiece(Position.of("h8")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(Position.of("a1")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(Position.of("h1")));
    }

    @Test
    public void place() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.place(piece, Position.of(position));

        assertEquals(piece, board.findPiece(Position.of(position)));
        System.out.println(board.showBoard());
    }

    @Test
    public void calculatePoint_같은_열에_Pawn_중복_X() throws Exception {
        board.initializeEmpty();

        board.place( Piece.createBlackPawn(), Position.of("b6"));
        board.place( Piece.createBlackQueen(), Position.of("e6"));
        board.place( Piece.createBlackKing(), Position.of("b8"));
        board.place( Piece.createBlackRook(), Position.of("c8"));

        board.place( Piece.createWhitePawn(), Position.of("f2"));
        board.place( Piece.createWhitePawn(), Position.of("g2"));
        board.place( Piece.createWhiteRook(), Position.of("e1"));
        board.place( Piece.createWhiteKing(), Position.of("f1"));

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void calculatePoint_같은_열에_Pawn_중복_O() throws Exception {
        board.initializeEmpty();

        board.place(Piece.createBlackKing(), Position.of("b8"));
        board.place(Piece.createBlackRook(), Position.of("c8"));
        board.place(Piece.createBlackPawn(), Position.of("a7"));
        board.place(Piece.createBlackPawn(), Position.of("c7"));
        board.place(Piece.createBlackBishop(), Position.of("d7"));
        board.place(Piece.createBlackPawn(), Position.of("b6"));
        board.place(Piece.createBlackQueen(), Position.of("e6"));

        board.place( Piece.createWhiteKnight(), Position.of("f4"));
        board.place( Piece.createWhiteQueen(), Position.of("g4"));
        board.place( Piece.createWhitePawn(), Position.of("f3"));
        board.place( Piece.createWhitePawn(), Position.of("h3"));
        board.place( Piece.createWhitePawn(), Position.of("f2"));
        board.place( Piece.createWhitePawn(), Position.of("g2"));
        board.place( Piece.createWhiteRook(), Position.of("e1"));
        board.place( Piece.createWhiteKing(), Position.of("f1"));

        board.showScore();

        assertEquals(20.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(19.5, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void board_sort_by_ascending() {
        board.initializeEmpty();

        board.place( Piece.createBlackPawn(), Position.of("b6"));
        board.place( Piece.createBlackQueen(), Position.of("e6"));
        board.place( Piece.createBlackKing(), Position.of("b8"));
        board.place( Piece.createBlackRook(), Position.of("c8"));

        board.place( Piece.createWhitePawn(), Position.of("f2"));
        board.place( Piece.createWhitePawn(), Position.of("g2"));
        board.place( Piece.createWhiteRook(), Position.of("e1"));
        board.place( Piece.createWhiteKing(), Position.of("f1"));

        System.out.println(board.sort(Order.ASC));
    }

    @Test
    public void moveTo_성공() throws Exception {
        board.initialize();

        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("b3");

        board.moveTo(sourcePosition, targetPosition);

        assertEquals(Piece.createBlank(), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(), board.findPiece(targetPosition));
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

        assertEquals(Piece.createBlank(), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(), board.findPiece(targetPosition));
    }

    @BeforeEach
    public void set() {
        white = Piece.createWhitePawn();
        black = Piece.createWhitePawn();
        board = new Board();
    }
}