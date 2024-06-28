package org.example.board;


import org.example.chess.board.Board;
import org.example.chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.chess.pieces.Piece.*;
import static org.example.chess.pieces.PieceFactory.*;
import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    public void create() {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard()
        );
    }

    @Test
    public void findPiece() {
        board.initialize();

        assertEquals(createBlackRook(), board.findPiece("a8"));
        assertEquals(createBlackRook(), board.findPiece("h8"));
        assertEquals(createWhiteRook(), board.findPiece("a1"));
        assertEquals(createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    public void move() {
        board.initialize();

        String position = "b5";
        Piece piece = createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", createBlackPawn());
        addPiece("e6", createBlackQueen());
        addPiece("b8", createBlackKing());
        addPiece("c8", createBlackRook());

        addPiece("f2", createWhitePawn());
        addPiece("g2", createWhitePawn());
        addPiece("e1", createWhiteRook());
        addPiece("f1", createWhiteKing());

        String s = board.showBoard();
        System.out.println(s);

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }


    @Test
    public void move_V2() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        // Piece 에 Position 필드가 있어야 하는가 ?
        assertEquals(createBlank(), board.findPiece(sourcePosition));
        assertEquals(createWhitePawn(), board.findPiece(targetPosition));
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
