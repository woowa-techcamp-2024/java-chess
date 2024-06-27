package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.chess.pieces.Piece.Color;
import org.example.chess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardInitializeManagerTest {

    private Board board;
    private BoardView boardView;
    private BoardInitializeManager boardInitializeManager;

    @BeforeEach
    void setUp() {
        board = new Board();
        boardView = new BoardView(board);
        boardInitializeManager = new BoardInitializeManager(board);
    }

    @Test
    void create() throws Exception {
        boardInitializeManager.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                boardView.showBoard()
        );
    }

    @Test
    void testCountPiecesByColorAndType() {
        boardInitializeManager.initialize();
        assertEquals(8, board.countPiecesByColorAndType(Color.BLACK, Type.PAWN));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.BISHOP));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.KNIGHT));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.ROOK));
        assertEquals(1, board.countPiecesByColorAndType(Color.BLACK, Type.KING));
        assertEquals(1, board.countPiecesByColorAndType(Color.BLACK, Type.QUEEN));

        assertEquals(8, board.countPiecesByColorAndType(Color.WHITE, Type.PAWN));
        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.BISHOP));
        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.KNIGHT));
        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.ROOK));
        assertEquals(1, board.countPiecesByColorAndType(Color.WHITE, Type.KING));
        assertEquals(1, board.countPiecesByColorAndType(Color.WHITE, Type.QUEEN));
    }
}