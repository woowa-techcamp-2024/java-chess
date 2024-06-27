package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Order;
import org.example.chess.pieces.global.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private static Piece black;
    private static Piece white;
    private Board board;

    @Test
    public void board_add_then_findPawn() {
        Board board = new Board();
        Position pos1 = Position.of(0, 0);
        board.move(white, pos1);
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
    public void move() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(piece, Position.of(position));

        assertEquals(piece, board.findPiece(Position.of(position)));
        System.out.println(board.showBoard());
    }

    @Test
    public void calculatePoint_같은_열에_Pawn_중복_X() throws Exception {
        board.initializeEmpty();

        board.move( Piece.createBlackPawn(), Position.of("b6"));
        board.move( Piece.createBlackQueen(), Position.of("e6"));
        board.move( Piece.createBlackKing(), Position.of("b8"));
        board.move( Piece.createBlackRook(), Position.of("c8"));

        board.move( Piece.createWhitePawn(), Position.of("f2"));
        board.move( Piece.createWhitePawn(), Position.of("g2"));
        board.move( Piece.createWhiteRook(), Position.of("e1"));
        board.move( Piece.createWhiteKing(), Position.of("f1"));

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void calculatePoint_같은_열에_Pawn_중복_O() throws Exception {
        board.initializeEmpty();

        board.move(Piece.createBlackKing(), Position.of("b8"));
        board.move(Piece.createBlackRook(), Position.of("c8"));
        board.move(Piece.createBlackPawn(), Position.of("a7"));
        board.move(Piece.createBlackPawn(), Position.of("c7"));
        board.move(Piece.createBlackBishop(), Position.of("d7"));
        board.move(Piece.createBlackPawn(), Position.of("b6"));
        board.move(Piece.createBlackQueen(), Position.of("e6"));

        board.move( Piece.createWhiteKnight(), Position.of("f4"));
        board.move( Piece.createWhiteQueen(), Position.of("g4"));
        board.move( Piece.createWhitePawn(), Position.of("f3"));
        board.move( Piece.createWhitePawn(), Position.of("h3"));
        board.move( Piece.createWhitePawn(), Position.of("f2"));
        board.move( Piece.createWhitePawn(), Position.of("g2"));
        board.move( Piece.createWhiteRook(), Position.of("e1"));
        board.move( Piece.createWhiteKing(), Position.of("f1"));

        board.showScore();

        assertEquals(20.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(19.5, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void board_sort_by_ascending() {
        board.initializeEmpty();

        board.move( Piece.createBlackPawn(), Position.of("b6"));
        board.move( Piece.createBlackQueen(), Position.of("e6"));
        board.move( Piece.createBlackKing(), Position.of("b8"));
        board.move( Piece.createBlackRook(), Position.of("c8"));

        board.move( Piece.createWhitePawn(), Position.of("f2"));
        board.move( Piece.createWhitePawn(), Position.of("g2"));
        board.move( Piece.createWhiteRook(), Position.of("e1"));
        board.move( Piece.createWhiteKing(), Position.of("f1"));

        System.out.println(board.sort(Order.ASC));
    }

    @BeforeEach
    public void set() {
        white = Piece.createWhitePawn();
        black = Piece.createWhitePawn();
        board = new Board();
    }
}