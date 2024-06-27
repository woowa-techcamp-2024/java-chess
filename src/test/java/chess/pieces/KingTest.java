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

public class KingTest {
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
    @DisplayName("King 기물이 원하는 대로 이동이 가능해야 한다")
    public void moveKing() {
        board.initialize();
        board.saveByPosition(PieceFactory.createKing(Color.BLACK, null), new Position(1, 0));
        board.saveByPosition(PieceFactory.createKnight(Color.BLACK, null), new Position(0, 0));

        chessGame.move("b8", "a8");
        assertEquals(PieceFactory.createKing(Color.BLACK, new Position("b8")), board.findPiece("b8"));

        chessGame.move("b8", "b9");
        assertEquals(PieceFactory.createKing(Color.BLACK, new Position("b8")), board.findPiece("b8"));

        chessGame.move("b8", "b7");
        assertEquals(PieceFactory.createKing(Color.BLACK, new Position("b7")), board.findPiece("b7"));
    }
}
