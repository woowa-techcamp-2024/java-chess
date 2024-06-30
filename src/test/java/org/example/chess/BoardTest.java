package org.example.chess;

import org.example.chess.pieces.*;
import org.example.chess.pieces.global.Order;
import org.example.chess.pieces.global.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.joining;
import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private static Piece black;
    private static Piece white;
    private Board board;

    @Test
    public void board에_piece를_추가할_수_있다() {
        Board board = new Board();
        Position pos1 = Position.of(0, 0);
        board.setPiece(pos1, white);
        assertEquals(1, board.countPieces());
        assertEquals(white, board.findPiece(pos1));
    }

    @Test
    public void board를_정해진_초기세팅으로_초기화하면_순서대로_프린트_가능하다() throws Exception {
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
    public void board를_정해진_초기세팅으로_초기화하면_Rook의_위치는_a1_h1_a8_h8에_존재한다() throws Exception {
        board.initialize();

        assertEquals(Rook.of(Piece.Color.BLACK), board.findPiece(Position.of("a8")));
        assertEquals(Rook.of(Piece.Color.BLACK), board.findPiece(Position.of("h8")));
        assertEquals(Rook.of(Piece.Color.WHITE), board.findPiece(Position.of("a1")));
        assertEquals(Rook.of(Piece.Color.WHITE), board.findPiece(Position.of("h1")));
    }

    @Test
    public void board에_Rook를_추가할_수_있다() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Rook.of(Piece.Color.BLACK);
        board.setPiece(Position.of(position), piece);

        assertEquals(piece, board.findPiece(Position.of(position)));
        System.out.println(board.showBoard());
    }

    @Test
    public void board를_빈칸으로_초기화하고_기물을_세우면_점수를_계산할_수_있다() throws Exception {
        board.initializeEmpty();

        board.setPiece(Position.of("b6"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("e6"), Queen.of(Piece.Color.BLACK));
        board.setPiece(Position.of("b8"), King.of(Piece.Color.BLACK));
        board.setPiece(Position.of("c8"), Rook.of(Piece.Color.BLACK));

        board.setPiece(Position.of("f2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("g2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("e1"), Rook.of(Piece.Color.WHITE));
        board.setPiece(Position.of("f1"), King.of(Piece.Color.WHITE));

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void board를_빈칸으로_초기화하고_기물을_세우고_같은_열에_pawn이_존재하면_특수한_점수를_계산할_수_있다() throws Exception {
        board.initializeEmpty();

        board.setPiece(Position.of("b8"), King.of(Piece.Color.BLACK));
        board.setPiece(Position.of("c8"), Rook.of(Piece.Color.BLACK));
        board.setPiece(Position.of("a7"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("c7"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("d7"), Bishop.of(Piece.Color.BLACK));
        board.setPiece(Position.of("b6"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("e6"), Queen.of(Piece.Color.BLACK));

        board.setPiece(Position.of("f4"), Knight.of(Piece.Color.WHITE));
        board.setPiece(Position.of("g4"), Queen.of(Piece.Color.WHITE));
        board.setPiece(Position.of("f3"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("h3"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("f2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("g2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("e1"), Rook.of(Piece.Color.WHITE));
        board.setPiece(Position.of("f1"), King.of(Piece.Color.WHITE));

        assertEquals(20.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(19.5, board.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    public void board를_빈칸으로_초기화하고_기물을_세우면_오름차순_출력이_가능하다() {
        board.initializeEmpty();

        board.setPiece(Position.of("b6"), Pawn.of(Piece.Color.BLACK));
        board.setPiece(Position.of("e6"), Queen.of(Piece.Color.BLACK));
        board.setPiece(Position.of("b8"), King.of(Piece.Color.BLACK));
        board.setPiece(Position.of("c8"), Rook.of(Piece.Color.BLACK));

        board.setPiece(Position.of("f2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("g2"), Pawn.of(Piece.Color.WHITE));
        board.setPiece(Position.of("e1"), Rook.of(Piece.Color.WHITE));
        board.setPiece(Position.of("f1"), King.of(Piece.Color.WHITE));

        Assertions.assertEquals("KkPppRrQ", board.sort(Order.ASC).stream()
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(joining()));
    }

    @Test
    public void board의_말을_움직이면_시작칸은_빈칸이고_도착칸은_피스가_존재한다() throws Exception {
        board.initialize();

        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("b3");

        board.moveTo(sourcePosition, targetPosition);

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