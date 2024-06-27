package chess.pieces;

import chess.Board;
import chess.ChessGame;
import chess.ChessView;
import chess.Position;
import chess.constant.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {
    private Board board;
    private ChessView chessView;
    private ChessGame chessGame;

    @BeforeEach
    public void setUp() {
        board = new Board();
        chessView = new ChessView(board);
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("Queen 기물이 원하는 대로 이동이 가능해야 한다")
    public void moveQueen() {
        board.initialize();
        board.saveByPosition(PieceFactory.createQueen(Color.BLACK, null), new Position(1, 0));

        chessGame.move("b8", "a8");
        assertEquals(PieceFactory.createQueen(Color.BLACK, new Position("a8")), board.findPiece("a8"));

        chessGame.move("a8", "e4");
        assertEquals(PieceFactory.createQueen(Color.BLACK, new Position("e4")), board.findPiece("e4"));
    }
}
